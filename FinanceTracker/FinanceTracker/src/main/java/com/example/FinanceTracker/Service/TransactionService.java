package com.example.FinanceTracker.Service;

import com.example.FinanceTracker.DTOs.TransactionDTO;
import com.example.FinanceTracker.Model.Transaction;
import com.example.FinanceTracker.Model.User;
import com.example.FinanceTracker.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public boolean deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
        return false;
    }

    public List<Transaction> getTransactionsByUser(User user) {
        return transactionRepository.findByUser(user);
    }

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        return optionalTransaction.orElse(null);
    }

    public TransactionDTO mapToDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        // map fields from transaction to transactionDTO
        transactionDTO.setId(transaction.getId());
        transactionDTO.setDescription(transaction.getDescription());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setDate(transaction.getDate().toString());  // Convert LocalDate to String
        transactionDTO.setUsername(transaction.getUser().getUsername());  // Set the username
        transactionDTO.setCategoryName(transaction.getCategory().getName());  // Set the category name

        return transactionDTO;
    }



}
