package com.example.digitalwallet.repository;

import com.example.digitalwallet.model.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {
    List<WalletTransaction> findByFromUserIdOrToUserId(Long fromUserId, Long toUserId);
}
