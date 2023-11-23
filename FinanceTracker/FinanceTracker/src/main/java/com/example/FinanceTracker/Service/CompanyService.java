package com.example.FinanceTracker.Service;

import com.example.FinanceTracker.Model.Budget;
import com.example.FinanceTracker.Model.Company;
import com.example.FinanceTracker.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Optional<Company> getCompanyById(Long companyId) {
        return companyRepository.findById(companyId);
    }

    public Company updateCompany(Company updatedCompany) {
        // Implement the logic to update the company here
        return companyRepository.save(updatedCompany);
    }

    public boolean deleteCompany(Long companyId) {
        // Implement the logic to delete the company here
        companyRepository.deleteById(companyId);
        return true; // Return true if the company was deleted successfully
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
