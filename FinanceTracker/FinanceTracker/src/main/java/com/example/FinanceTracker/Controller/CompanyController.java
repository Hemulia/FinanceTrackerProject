package com.example.FinanceTracker.Controller;


import com.example.FinanceTracker.DTOs.BudgetDTO;
import com.example.FinanceTracker.Model.Budget;
import com.example.FinanceTracker.Model.Company;
import com.example.FinanceTracker.Model.User;
import com.example.FinanceTracker.Service.CompanyService;
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
@RequestMapping("/companies")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    // Get all companies
    //WORKS IN POSTMAN
    @GetMapping
    public List<Company> getAllCompanies() {
        logger.info("Getting all the companies");

        return companyService.getAllCompanies();
    }


    // Create a new company
    // WORKS
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        try {
            Company createdCompany = companyService.createCompany(company);
            logger.info("Company created successfully: {}", createdCompany.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
        } catch (Exception e) {
            logger.error("Error creating company: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Read a company by ID
    // WORKS
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Optional<Company> company = companyService.getCompanyById(id);
        if (company.isPresent()) {
            return ResponseEntity.ok(company.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an existing company
    // WORKS
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        company.setId(id);
        Company updatedCompany = companyService.updateCompany(company);
        if (updatedCompany != null) {
            logger.info("Company updated successfully: {}", updatedCompany.getName());
            return ResponseEntity.ok(updatedCompany);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a company by ID
    // WORKS
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompany(id);
        if (deleted) {
            logger.info("Company deleted successfully. ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
