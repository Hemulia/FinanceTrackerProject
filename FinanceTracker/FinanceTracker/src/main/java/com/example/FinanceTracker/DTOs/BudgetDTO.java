package com.example.FinanceTracker.DTOs;

import java.math.BigDecimal;

public class BudgetDTO {

    private Long id;

    private String name;

    private BigDecimal amount;

    private String username;

    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
