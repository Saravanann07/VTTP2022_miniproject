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

    public Optional<List<Transaction>> getUserTransactions (int userId) {

        Optional<List<Transaction>> opt = transactionRepo.getUserTransactions(userId);

        if (opt.isEmpty()) {
            return Optional.empty();
        }

        List<Transaction> transactionsList = opt.get();

        return Optional.of(transactionsList);
    }

    public Optional<List<Transaction>> getCompanyTransactions (String symbol, int userId) {

        Optional<List<Transaction>> companyList = transactionRepo.getUserCompanyTransactions(symbol, userId);

        if (companyList.isEmpty()) {
            return Optional.empty();
        }

        List<Transaction> purchases = companyList.get();

        return Optional.of(purchases);
    }


    // public void addTransaction(Integer userId, Date purchaseDate, String symbol, String companyName, Integer quantity, Double stockPrice, Double totalPrice, Double stockStatus) throws TransactionException{

    //     Integer transaction = transactionRepo.transactionAlreadyAdded(symbol, userId);

    //     if (transaction == 1)
    //         throw new TransactionException("User has already added %s to their stock purchases".formatted(symbol));

    //     if (!transactionRepo.addTransaction(userId, purchaseDate, symbol, companyName, quantity, stockPrice, totalPrice, stockStatus))
    //         throw new TransactionException("Cannot add %s into your stock purchases. Please contact admin of StockStatus".formatted(symbol));

    //         addTransaction(userId, purchaseDate, symbol, companyName, quantity, stockPrice, totalPrice, stockStatus);
    // }

    public void addTransaction(Integer userId, Date purchaseDate, String symbol, String companyName, Integer quantity, Double stockPrice, Double totalPrice, Double stockStatus) throws TransactionException{

        Integer transaction = transactionRepo.transactionAlreadyAdded(symbol, stockPrice, userId);

        if (transaction == 1)
        {
            throw new TransactionException("User has already added %s to their stock purchases".formatted(symbol));
        } else{
            try{
                transactionRepo.addTransaction(userId, purchaseDate, symbol, companyName, quantity, stockPrice, totalPrice, stockStatus);
            } catch (Exception e) {
            throw new TransactionException(
                "Cannot add %s into your stock purchases. Please contact admin of StockStatus".formatted(symbol));
            }
        }
    }
    
}
