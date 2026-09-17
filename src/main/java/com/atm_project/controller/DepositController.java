package com.atm_project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atm_project.exception.InvalidAmountException;
import com.atm_project.model.DepositRequest;
import com.atm_project.model.DepositResponse;
import com.atm_project.service.DepositService;

@RestController
public class DepositController {

    private DepositService depositService =
            new DepositService();

    @PostMapping("/deposit")
    public DepositResponse deposit(
            @RequestBody DepositRequest request)
            throws InvalidAmountException, Exception {

        double updatedBalance =
                depositService.deposit(
                        request.getAccountId(),
                        request.getAmount()
                );

        DepositResponse response =
                new DepositResponse(
                        "Amount deposited successfully",
                        updatedBalance
                );

        return response;
    }
}