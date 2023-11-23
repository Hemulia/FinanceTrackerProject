package com.example.FinanceTracker.Model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
public class Category {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public void setId(Long id) {
        this.id = id;
    }

}
