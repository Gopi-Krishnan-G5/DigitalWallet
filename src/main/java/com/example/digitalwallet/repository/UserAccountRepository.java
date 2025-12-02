package com.example.digitalwallet.repository;

import com.example.digitalwallet.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {}
