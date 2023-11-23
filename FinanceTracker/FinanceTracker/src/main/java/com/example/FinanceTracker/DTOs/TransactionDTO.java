package com.example.FinanceTracker.DTOs;

import java.math.BigDecimal;

public class TransactionDTO {
    private Long id;
    private String description;
    private BigDecimal amount;
    private String date;
    private String username;
    private String categoryName;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
