package vttp2022.project.Stock.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import vttp2022.project.Stock.models.Transaction;

import static vttp2022.project.Stock.repositories.Queries.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Repository
public class TransactionRepository {

    @Autowired
    private JdbcTemplate template;
    
    // public boolean addTransaction(Integer userId, Transaction t) {
    //     int count = template.update(SQL_INSERT_TRANSACTION, t.getPurchaseDate(), t.getSymbol(), t.getCompanyName(),
    //     t.getQuantity(), t.getStockPrice(), t.getTotalPrice(), userId);

    //     return 1 == count;
    // }

    public boolean addTransaction(Integer userId, Date purchaseDate, String symbol, String companyName, Integer quantity, Double stockPrice, Double totalPrice, Double stockStatus) {
        int count = template.update(SQL_INSERT_TRANSACTION, purchaseDate, symbol, companyName,
        quantity, stockPrice, totalPrice, userId, stockStatus);

        return 1 == count;
    }


    // public Integer transactionAlreadyAdded(String symbol, Integer userId) {
    //     final SqlRowSet rs = template.queryForRowSet(SQL_CHECK_IF_USER_HAS_MADE_TRANSACTIONS, symbol, userId);

    //     if (!rs.next())
    //         return 0;
    //     return rs.getInt("company_added");

    // }

    public Integer transactionAlreadyAdded(String symbol, Double stockPrice,Integer userId) {
        final SqlRowSet rs = template.queryForRowSet(SQL_CHECK_IF_USER_HAS_MADE_PURCHASE, symbol, stockPrice, userId);

        if (!rs.next())
            return 0;
        return rs.getInt("purchase_added");

    }

    public Optional<List<Transaction>> getUserTransactions(int userId) {

        List<Transaction> userTransactions = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_USER_TRANSACTIONS, userId);

        while (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setTransactionId(rs.getInt("transaction_id")); //int
            transaction.setPurchaseDate(rs.getDate("purchase_date")); // date
            transaction.setSymbol(rs.getString("symbol")); // string
            transaction.setCompanyName(rs.getString("company_name")); // string;
            transaction.setQuantity(rs.getInt("quantity")); // int
            transaction.setStockPrice(rs.getDouble("stock_price")); // double
            transaction.setTotalPrice(rs.getDouble("total_price")); // double
            transaction.setStockStatus(rs.getDouble("stock_status")); //double

            userTransactions.add(transaction);
        }
        return Optional.of(userTransactions);
    }

    public Optional<List<Transaction>> getUserCompanyTransactions(String symbol, int userId) {

        List<Transaction> companyTransactions = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_USER_COMPANY_TRANSACTIONS, symbol, userId);

        while (rs.next()) {
            Transaction company = new Transaction();
            company.setTransactionId(rs.getInt("transaction_id")); //int
            company.setPurchaseDate(rs.getDate("purchase_date")); // date
            company.setSymbol(rs.getString("symbol")); // string
            company.setCompanyName(rs.getString("company_name")); // string;
            company.setQuantity(rs.getInt("quantity")); // int
            company.setStockPrice(rs.getDouble("stock_price")); // double
            company.setTotalPrice(rs.getDouble("total_price")); // double
            company.setStockStatus(rs.getDouble("stock_status")); //double

            companyTransactions.add(company);
        }
        return Optional.of(companyTransactions);    
    }

    // public Optional<List<Transaction>> updateStockStatus (String symbol, double stockStatus, int userId) {

    //     List<Transaction> stockMarketValue = new LinkedList<>();
    // }

  
}
