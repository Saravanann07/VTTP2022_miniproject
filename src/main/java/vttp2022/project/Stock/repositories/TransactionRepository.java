package vttp2022.project.Stock.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.project.Stock.models.Transaction;

import static vttp2022.project.Stock.repositories.Queries.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Repository
public class TransactionRepository {

    @Autowired
    private JdbcTemplate template;
    
    public boolean addTransaction(Integer userId, Transaction t) {
        int count = template.update(SQL_INSERT_TRANSACTION, t.getPurchaseDate(), t.getSymbol(), t.getCompanyName(),
        t.getQuantity(), t.getTotalPrice(), userId);

        return 1 == count;
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
            transaction.setStockPrice(rs.getFloat("stock_price")); // double
            transaction.setTotalPrice(rs.getFloat("total_price")); // double

            userTransactions.add(transaction);
        }
        return Optional.of(userTransactions);
    }
}
