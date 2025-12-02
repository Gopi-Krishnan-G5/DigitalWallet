package com.example.digitalwallet.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double balance = 0.0;
}
