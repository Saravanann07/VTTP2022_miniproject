package vttp2022.project.Stock.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.project.Stock.exceptions.TransactionException;
import vttp2022.project.Stock.exceptions.UserException;
import vttp2022.project.Stock.models.Transaction;
import vttp2022.project.Stock.models.User;
import vttp2022.project.Stock.repositories.UserRepository;
import vttp2022.project.Stock.services.StockService;
import vttp2022.project.Stock.services.TransactionService;
import vttp2022.project.Stock.services.UserService;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired 
    private UserService userSvc;

    @Autowired
    private TransactionService transactionSvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockService stockSvc;

    @GetMapping(path="")
    public String AddUser(Model model){
        return "login_page";
    }

    @GetMapping(path="/logout")
    public String getLogout(HttpSession sess) {
        sess.invalidate();
        return "login_page";
    }

    @PostMapping(path = "/createUser")
    public ModelAndView createUser(@RequestBody MultiValueMap<String, String> form) {

        ModelAndView mvc = new ModelAndView();

        String username = form.getFirst("username");
        String password = form.getFirst("password");

        try {
            userSvc.createUser(username, password);
        } catch (UserException ex) {
            mvc.addObject("messageUser", "error: %s".formatted(ex.getReason()));
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            ex.printStackTrace();
        }

        mvc.addObject("messageUser", "%s has been successully registered".formatted(username));
        mvc.setViewName("login_page");
        return mvc;
    }

    @PostMapping(path="/authenticate")
    public ModelAndView postLogin(
        @RequestBody MultiValueMap<String, String> payload, HttpSession sess) {
            // name in login_page should match multivaluemap (getFirst)
            String username = payload.getFirst("username");
            String password = payload.getFirst("password");
            System.out.println(">>>>>>>>" + payload);
            User user = userRepository.getUser(username, password);

            ModelAndView mvc = new ModelAndView();
            
            // not successful
            if (!userSvc.authenticate(username, password)) {
                mvc.setStatus(HttpStatus.UNAUTHORIZED);
                mvc.setViewName("login_error");
                // return mvc;

             //successful   
            } else{     
            mvc.addObject("username", username);

            Optional<List<Transaction>> optTransaction = transactionSvc.getUserTransactions(user.getUserId());
            List<Transaction> transactionList = optTransaction.get();
            for (Transaction trans : transactionList) {
                //Gets market value of stocks everytime user refreshes page
                Double marketPrice = stockSvc.getQuote(trans.getSymbol());
                trans.setStockStatus(marketPrice*trans.getQuantity());
                System.out.println(">>>>>" + marketPrice);
                System.out.println(">>>>>>" + trans.getStockStatus());
            }

            
            mvc.addObject("transactionList", transactionList);
            mvc.setViewName("HomePage");
            mvc.setStatus(HttpStatus.ACCEPTED);
            mvc.setViewName("Homepage");
            
            sess.setAttribute("username", username);
            sess.setAttribute("password", password);
            }
                
            return mvc;

        }

    @PostMapping(path="/addTransaction")
    public ModelAndView addStockPurchase(@RequestBody MultiValueMap<String, String> form, HttpSession sess) {
        
            String username = (String) sess.getAttribute("username");
            String password = (String) sess.getAttribute("password");
            


        ModelAndView mvc = new ModelAndView();
        String dateStr = form.getFirst("purchaseDate");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date purchaseDate;
        try {
            purchaseDate = format.parse(dateStr);
        } catch (ParseException e) {
            purchaseDate = null;
            e.printStackTrace();
        }

        String symbol = form.getFirst("symbol");
        String companyName = form.getFirst("companyName");
        Integer quantity = Integer.parseInt(form.getFirst("quantity"));
        Double stockPrice = Double.parseDouble(form.getFirst("stockPrice"));
        Double totalPrice = Double.parseDouble(form.getFirst("totalPrice"));
        User user = userRepository.getUser(username, password);
        
    
        try {
            transactionSvc.addTransaction(user.getUserId(), purchaseDate, symbol, companyName, quantity, stockPrice, totalPrice);
        } catch (TransactionException ex) {
            mvc.addObject("transactionMessage", "error: %s".formatted(ex.getReason()));
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            ex.printStackTrace();
            
        }
        System.out.println(">>>>>>>>>errrrorrrrr");

        Optional<List<Transaction>> optTransaction = transactionSvc.getUserTransactions(user.getUserId());
        List<Transaction> transactionList = optTransaction.get();
        
        for (Transaction trans : transactionList) {
            
            Double marketPrice = stockSvc.getQuote(trans.getSymbol());
            trans.setStockStatus(marketPrice*trans.getQuantity());
            System.out.println(">>>>>" + marketPrice);
            System.out.println(">>>>>>" + trans.getStockStatus());
        }
        mvc.addObject("username", username);
        mvc.addObject("transactionUser", "%s has been successfully added to your stock purchases".formatted(symbol));
        mvc.addObject("transactionList", transactionList);
        // mvc.addObject("stockStatus", stockStatus);
        mvc.setViewName("Homepage");
        return mvc;
    }

    // Group all purchases made by user from a specific company. Identified by symbol/ticker
    @GetMapping(path= "/company") 
        public ModelAndView getUserCompanyPurchase(@RequestParam("symbol") String symbol, HttpSession sess) {

            String username = (String) sess.getAttribute("username");
            String password = (String) sess.getAttribute("password");

            ModelAndView mvc = new ModelAndView();

            User user = userRepository.getUser(username, password);

            Optional<List<Transaction>> optCompany = transactionSvc.getCompanyTransactions(symbol, user.getUserId());
            List<Transaction> allPurchasesList = optCompany.get();

            for (Transaction trans : allPurchasesList) {
            
                Double marketPrice = stockSvc.getQuote(trans.getSymbol());
                trans.setStockStatus(marketPrice*trans.getQuantity());
                System.out.println(">>>>>" + marketPrice);
                System.out.println(">>>>>>" + trans.getStockStatus());
            }

            mvc.addObject("allPurchasesList", allPurchasesList);
            // mvc.setStatus(HttpStatus.OK);
            mvc.setViewName("company");

            return mvc;
        }


    
}