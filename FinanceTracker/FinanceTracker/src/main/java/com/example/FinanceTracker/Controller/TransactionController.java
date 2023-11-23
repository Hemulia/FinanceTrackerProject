package com.example.FinanceTracker.Controller;

import com.example.FinanceTracker.DTOs.TransactionDTO;
import com.example.FinanceTracker.Model.Category;
import com.example.FinanceTracker.Model.Transaction;
import com.example.FinanceTracker.Model.User;
import com.example.FinanceTracker.Service.CategoryService;
import com.example.FinanceTracker.Service.TransactionService;
import com.example.FinanceTracker.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    // Get all transactions
    //WORKS IN POSTMAN
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        List<TransactionDTO> transactionDTOs = transactions.stream()
                .map(transaction -> transactionService.mapToDTO(transaction))
                .collect(Collectors.toList());

        return new ResponseEntity<>(transactionDTOs, HttpStatus.OK);
    }

    // Get a specific transaction using its id
    // WORKS IN POSTMAN
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        if (transaction != null) {
            TransactionDTO transactionDTO = transactionService.mapToDTO(transaction);
            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //WORKS IN POSTMAN
    // Create a new transaction
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(
            @RequestBody Transaction transaction,
            @RequestParam Long userId,
            @RequestParam Long categoryId) {

        // Retrieve the user and category from the database
        Optional<User> optionalUser = userService.getUserById(userId);
        Category category = categoryService.getCategoryById(categoryId);

        // Check if the user and category exist
        if (optionalUser.isEmpty() || category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Unwrap the Optional<User> to get the actual User
        User user = optionalUser.get();

        // Associate the user and category with the transaction
        transaction.setUser(user);
        transaction.setCategory(category);

        // Save the transaction
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }


    // Update an existing transaction
    // WORKS IN POSTMAN
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        Transaction updatedTransaction = transactionService.updateTransaction(id, transaction);
        if (updatedTransaction != null) {
            return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // WORKS IN POSTMAN
    // Delete a transaction by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        boolean deleted = transactionService.deleteTransaction(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
