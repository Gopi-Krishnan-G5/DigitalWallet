package com.example.digitalwallet.service;

import com.example.digitalwallet.model.UserAccount;
import com.example.digitalwallet.model.WalletTransaction;
import com.example.digitalwallet.repository.UserAccountRepository;
import com.example.digitalwallet.repository.WalletTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
    private final UserAccountRepository userRepo;
    private final WalletTransactionRepository transRepo;

    public WalletService(UserAccountRepository userRepo, WalletTransactionRepository transRepo) {
        this.userRepo = userRepo;
        this.transRepo = transRepo;
    }

    public UserAccount createUser(String name) {
        UserAccount user = new UserAccount();
        user.setName(name);
        return userRepo.save(user);
    }

    public UserAccount addMoney(Long id, double amount) {
        UserAccount user = userRepo.findById(id).orElseThrow();
        user.setBalance(user.getBalance() + amount);
        return userRepo.save(user);
    }

    public String transfer(Long fromId, Long toId, double amount) {
        UserAccount from = userRepo.findById(fromId).orElseThrow();
        UserAccount to = userRepo.findById(toId).orElseThrow();

        if (from.getBalance() < amount) return "Insufficient Balance";

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        userRepo.save(from);
        userRepo.save(to);

        WalletTransaction tx = new WalletTransaction();
        tx.setFromUserId(fromId);
        tx.setToUserId(toId);
        tx.setAmount(amount);
        transRepo.save(tx);

        return "Transfer Successful";
    }

    public List<WalletTransaction> getTransactions(Long userId) {
        return transRepo.findByFromUserIdOrToUserId(userId, userId);
    }

    public List<UserAccount> listUsers() {
        return userRepo.findAll();
    }
}
