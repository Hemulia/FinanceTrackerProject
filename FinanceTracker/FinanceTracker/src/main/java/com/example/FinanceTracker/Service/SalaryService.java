package com.example.FinanceTracker.Service;

import com.example.FinanceTracker.DTOs.SalaryDTO;
import com.example.FinanceTracker.Model.Salary;
import com.example.FinanceTracker.Repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    public Salary createSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    public Salary updateSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    public boolean deleteSalary(Long salaryId) {
        salaryRepository.deleteById(salaryId);
        return false;
    }

    public Optional<Salary> getSalaryById(Long id) { return salaryRepository.findById(id);
    }

    public SalaryDTO mapToDTO(Salary salary) {
        SalaryDTO salaryDTO  = new SalaryDTO();

        // map fields from salaries to salaryDTO
        salaryDTO.setId(salary.getId());
        salaryDTO.setSalary(salary.getSalary());
        salaryDTO.setLocalDate(salary.getLocalDate());

        // Check if user is not null before accessing properties
        if (salary.getUser() != null) {
            salaryDTO.setUsername(salary.getUser().getUsername());  // Set the username
        }

        if (salary.getCompany() != null) {
            salaryDTO.setCompanyname(salary.getCompany().getCompanyname());  // Set the username
        }

        return salaryDTO;
    }

}
