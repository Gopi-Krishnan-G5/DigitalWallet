package com.example.digitalwallet.controller;

import com.example.digitalwallet.model.UserAccount;
import com.example.digitalwallet.model.WalletTransaction;
import com.example.digitalwallet.service.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", service.listUsers());
        return "index";
    }

    @PostMapping("/create")
    public String createUser(@RequestParam String name) {
        service.createUser(name);
        return "redirect:/wallet/";
    }

    @PostMapping("/add")
    public String addMoney(@RequestParam Long userId, @RequestParam double amount) {
        service.addMoney(userId, amount);
        return "redirect:/wallet/";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromId, @RequestParam Long toId, @RequestParam double amount) {
        service.transfer(fromId, toId, amount);
        return "redirect:/wallet/";
    }

    @GetMapping("/transactions/{id}")
    public String viewTransactions(@PathVariable Long id, Model model) {
        List<WalletTransaction> list = service.getTransactions(id);
        model.addAttribute("transactions", list);
        return "transactions";
    }
}
