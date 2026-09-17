package com.atm_project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atm_project.exception.UserNotFoundException;
import com.atm_project.model.Transaction;
import com.atm_project.service.TransactionService;

@RestController
public class TransactionController {

    private TransactionService transactionService =
            new TransactionService();

    @GetMapping("/transactions/{accountId}")
    public List<Transaction> getTransactions(
            @PathVariable int accountId)
            throws UserNotFoundException, Exception {

        return transactionService.getTransactions(
                accountId
        );
    }
}
