package com.example.FinanceTracker.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name="user")
@Data
public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String username;
    @Getter
    @JsonIgnore
    private String password;
    private String firstname;
    private String lastname;

    @OneToMany(mappedBy= "user", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Long getUserid() {
        return id;
    }
}
