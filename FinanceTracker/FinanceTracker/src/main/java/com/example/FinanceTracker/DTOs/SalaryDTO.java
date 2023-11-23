package com.example.FinanceTracker.DTOs;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalaryDTO {

    private Long id;

    private BigDecimal salary;

    private LocalDate localDate;  // Match the name with the entity

    private String companyname;

    private String username;

    public Long getId() {
        return id;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setLocalDate(LocalDate date) {
        this.localDate = localDate;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
