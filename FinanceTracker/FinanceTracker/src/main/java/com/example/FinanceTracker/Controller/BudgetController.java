package com.example.FinanceTracker.Controller;

import com.example.FinanceTracker.DTOs.BudgetDTO;
import com.example.FinanceTracker.DTOs.TransactionDTO;
import com.example.FinanceTracker.Model.Budget;
import com.example.FinanceTracker.Model.Transaction;
import com.example.FinanceTracker.Service.BudgetService;
import lombok.RequiredArgsConstructor;
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
@CrossOrigin
@RequestMapping("/budgets")
@RequiredArgsConstructor
@Slf4j
public class BudgetController {
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private BudgetService budgetService;


    // Get all budgets
    //WORKS IN POSTMAN
    @GetMapping
    public ResponseEntity<List<BudgetDTO>> getAllBudgets() {
        List<Budget> budgets = budgetService.getAllBudgets();
        List<BudgetDTO> budgetsDTOs = budgets.stream()
                .map(budget -> budgetService.mapToDTO(budget))
                .collect(Collectors.toList());

        return new ResponseEntity<>(budgetsDTOs, HttpStatus.OK);
    }


    // Create a new budget
    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        try {
            Budget createdBudget = budgetService.createBudget(budget);
            logger.info("Budget created successfully: {}", createdBudget.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBudget);
        } catch (Exception e) {
            logger.error("Error creating company: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Read a budget by ID
    //WORKS
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        Optional<Budget> budget = budgetService.getBudgetById(id);
        if (budget.isPresent()) {
            return ResponseEntity.ok(budget.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an existing budget
    // WORKS
    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
        budget.setId(id);
        Budget updatedBudget = budgetService.updateBudget(budget);
        if (updatedBudget != null) { // Corrected from updatedBudget()
            logger.info("Budget updated successfully: {}", updatedBudget.getName());
            return ResponseEntity.ok(updatedBudget);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Delete a budget by ID
    //WORKS
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        boolean deleted = budgetService.deleteBudget(id);
        if (deleted) {
            logger.info("Budget deleted successfully. ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
