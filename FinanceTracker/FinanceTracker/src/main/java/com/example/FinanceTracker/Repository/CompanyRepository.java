package com.example.FinanceTracker.Repository;

import com.example.FinanceTracker.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String testCompany);
}
