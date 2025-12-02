package com.example.digitalwallet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromUserId;
    private Long toUserId;
    private double amount;
    private LocalDateTime dateTime = LocalDateTime.now();
}
