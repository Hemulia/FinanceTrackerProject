package com.example.FinanceTracker.Repository;

import com.example.FinanceTracker.Model.Transaction;
import com.example.FinanceTracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
