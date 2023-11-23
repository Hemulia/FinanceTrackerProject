package com.example.FinanceTracker.Controller;

import com.example.FinanceTracker.DTOs.SalaryDTO;
import com.example.FinanceTracker.DTOs.TransactionDTO;
import com.example.FinanceTracker.Model.Salary;
import com.example.FinanceTracker.Model.Transaction;
import com.example.FinanceTracker.Service.SalaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/salaries")
@RequiredArgsConstructor
@Slf4j
public class SalaryController {

    private static final Logger logger = LoggerFactory.getLogger(SalaryController.class);

    @Autowired
    private SalaryService salaryService;

    // Get all salaries
    // WORKS
    @GetMapping
    public ResponseEntity<List<SalaryDTO>> getAllSalaries() {
        List<Salary> salaries = salaryService.getAllSalaries();
        List<SalaryDTO> salaryDTOs = salaries.stream()
                .map(salary -> salaryService.mapToDTO(salary))
                .collect(Collectors.toList());

        return new ResponseEntity<>(salaryDTOs, HttpStatus.OK);
    }


    // Create a new salary
    // WORKS
    @PostMapping
    public ResponseEntity<Salary> createSalary(@RequestBody Salary salary) {
        try {
            Salary createdSalary = salaryService.createSalary(salary);
            logger.info("Salary created successfully: {}", createdSalary.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSalary);
        } catch (Exception e) {
            logger.error("Error creating salary: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Read a salary by ID
    // WORKS
    @GetMapping("/{id}")
    public ResponseEntity<Salary> getSalaryById(@PathVariable Long id) {
        Optional<Salary> salary = salaryService.getSalaryById(id);
        if (salary.isPresent()) {
            return ResponseEntity.ok(salary.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an existing salary
    // WORKS
    @PutMapping("/{id}")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long id, @RequestBody Salary salary) {
        salary.setId(id);
        Salary updatedSalary = salaryService.updateSalary(salary);
        if (updatedSalary != null) {
            logger.info("Salary updated successfully: ID {}", updatedSalary.getId());
            return ResponseEntity.ok(updatedSalary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a salary by ID
    // WORKS
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        boolean deleted = salaryService.deleteSalary(id);
        if (deleted) {
            logger.info("Salary deleted successfully. ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
