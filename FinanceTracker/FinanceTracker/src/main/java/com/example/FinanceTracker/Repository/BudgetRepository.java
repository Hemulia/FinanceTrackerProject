package com.example.FinanceTracker.Repository;

import com.example.FinanceTracker.Model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    // You can add custom query methods here if needed
}
