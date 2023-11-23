package com.example.FinanceTracker.Repository;

import com.example.FinanceTracker.Model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
