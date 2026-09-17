package com.atm_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atm_project.exception.UserNotFoundException;
import com.atm_project.model.BalanceResponse;
import com.atm_project.model.User;
import com.atm_project.service.BalanceService;

@RestController
public class BalanceController {

    private BalanceService balanceService =
            new BalanceService();

    @GetMapping("/balance/{accountId}")
    public BalanceResponse getBalance(
            @PathVariable int accountId)
            throws UserNotFoundException {

        User user =
                balanceService.getBalance(accountId);

        BalanceResponse response =
                new BalanceResponse(
                        user.getAccountId(),
                        user.getCustomerName(),
                        user.getBalance()
                );

        return response;
    }
}