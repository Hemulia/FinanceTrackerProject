package com.example.FinanceTracker.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="salary")
@Data
public class Salary {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private BigDecimal salary;

    @Column(name = "local_date")
    private LocalDate localDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User User;

    public void setId(Long id) {
        this.Id = id;

    }

}
