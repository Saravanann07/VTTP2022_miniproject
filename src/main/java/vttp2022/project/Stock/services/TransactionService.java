package vttp2022.project.Stock.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
