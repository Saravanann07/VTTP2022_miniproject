package vttp2022.project.Stock.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.project.Stock.exceptions.TransactionException;
import vttp2022.project.Stock.models.Transaction;
import vttp2022.project.Stock.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    

    public Optional<List<Transaction>> getCompanyTransactions (String symbol, int userId) {

        Optional<List<Transaction>> companyList = transactionRepo.getUserCompanyTransactions(symbol, userId);

        if (companyList.isEmpty()) {
            return Optional.empty();
        }

        List<Transaction> purchases = companyList.get();

        return Optional.of(purchases);
    }

    public void addTransaction(Integer userId, Date purchaseDate, String symbol, String companyName, Integer quantity, Double stockPrice, Double totalPrice) throws TransactionException{

        Integer transaction = transactionRepo.transactionAlreadyAdded(symbol, stockPrice, userId);

        if (transaction == 1)
        {
            throw new TransactionException("User has already added %s to their stock purchases".formatted(symbol));
        } else{
            try{
                transactionRepo.addTransaction(userId, purchaseDate, symbol, companyName, quantity, stockPrice, totalPrice);
            } catch (Exception ex) {
            throw new TransactionException(
                "Cannot add %s into your stock purchases. Please contact admin of StockStatus".formatted(symbol));
            }
        }
    }

    public Optional<List<Transaction>> getDateTransactions (int userId) {

        Optional<List<Transaction>> opt = transactionRepo.getTransactionsDate(userId);

        if (opt.isEmpty()) {
            return Optional.empty();
        }

        List<Transaction> datesList = opt.get();

        return Optional.of(datesList);
    }

    
    
}
