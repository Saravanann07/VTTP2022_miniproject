package vttp2022.project.Stock.controllers;

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
import org.springframework.web.servlet.ModelAndView;

import vttp2022.project.Stock.services.UserService;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired 
    private UserService userSvc;

    @GetMapping(path="")
    public String AddUser(Model model){
        return "login_page";
    }

    @GetMapping(path="/logout")
    public String getLogout(HttpSession sess) {
        sess.invalidate();
        return "login_page";
    }

    @PostMapping(path="/authenticate")
    public ModelAndView postLogin(
        @RequestBody MultiValueMap<String, String> payload, HttpSession sess) {
            // name in login_page should match multivaluemap (getFirst)
            String username = payload.getFirst("username");
            String password = payload.getFirst("password");
            System.out.println(">>>>>>>>" + payload);

            ModelAndView mvc = new ModelAndView();
            
            // not successful
            if (!userSvc.authenticate(username, password)) {
                mvc.setStatus(HttpStatus.UNAUTHORIZED);
                mvc.setViewName("error");
                // return mvc;

             //successful   
            } else{     
            // mvc.addObject("username", username);
            // mvc.setStatus(HttpStatus.ACCEPTED);
            // mvc.setViewName("Homepage");
            
            sess.setAttribute("username", username);
            mvc = new ModelAndView("redirect:/protected/Homepage");
            // return mvc;
            }
                
            return mvc;

        }
    
}