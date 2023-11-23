package com.example.FinanceTracker.Service;

import com.example.FinanceTracker.DTOs.BudgetDTO;
import com.example.FinanceTracker.DTOs.TransactionDTO;
import com.example.FinanceTracker.Model.Budget;
import com.example.FinanceTracker.Model.Transaction;
import com.example.FinanceTracker.Repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public Budget updateBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public boolean deleteBudget(Long budgetId) {
        budgetRepository.deleteById(budgetId);
        return false;
    }

    public Optional<Budget> getBudgetById(Long id) { return budgetRepository.findById(id);
    }

    public BudgetDTO mapToDTO(Budget budget) {
        BudgetDTO budgetDTO = new BudgetDTO();

        // map fields from budgets to budgetDTO
        budgetDTO.setId(budget.getId());
        budgetDTO.setName(budget.getName());
        budgetDTO.setAmount(budget.getAmount());

        // Check if user is not null before accessing properties
        if (budget.getUser() != null) {
            budgetDTO.setUsername(budget.getUser().getUsername());  // Set the username
        }

        return budgetDTO;
    }

}
