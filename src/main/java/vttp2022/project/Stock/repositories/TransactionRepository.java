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
    

    public boolean addTransaction(Integer userId, Date purchaseDate, String symbol, String companyName, Integer quantity, Double stockPrice, Double totalPrice) {
        int count = template.update(SQL_INSERT_TRANSACTION, purchaseDate, symbol, companyName,
        quantity, stockPrice, totalPrice, userId);

        return 1 == count;
    }

    public Integer transactionAlreadyAdded(String symbol, Double stockPrice,Integer userId) {
        final SqlRowSet rs = template.queryForRowSet(SQL_CHECK_IF_USER_HAS_MADE_PURCHASE, symbol, stockPrice, userId);

        if (!rs.next())
            return 0;
        return rs.getInt("purchase_added");

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
            // company.setStockStatus(rs.getDouble("stock_status")); //double

            companyTransactions.add(company);
        }
        return Optional.of(companyTransactions);    
    }  

    public Optional<List<Transaction>> getTransactionsDate(int userId) {

        List<Transaction> dateTransactions = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_SORT_TRANSACTIONS_BY_DATE, userId);

        while (rs.next()) {
            Transaction date = new Transaction();
            date.setTransactionId(rs.getInt("transaction_id")); //int
            date.setPurchaseDate(rs.getDate("purchase_date")); // date
            date.setSymbol(rs.getString("symbol")); // string
            date.setCompanyName(rs.getString("company_name")); // string;
            date.setQuantity(rs.getInt("quantity")); // int
            date.setStockPrice(rs.getDouble("stock_price")); // double
            date.setTotalPrice(rs.getDouble("total_price")); // double
            // transaction.setStockStatus(rs.getDouble("stock_status")); //double

            dateTransactions.add(date);
        }
        return Optional.of(dateTransactions);

    }

    // public boolean deleteTransaction (Double totalPrice) {
    //         int count = template.update(SQL_DELETE_USER_TRANSACTIONS, totalPrice);
    //         return 1 == count;
    //     }

}
