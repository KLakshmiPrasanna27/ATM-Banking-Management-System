package com.atm_project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.atm_project.exception.UserNotFoundException;
import com.atm_project.exception.InsufficientBalanceException;
import com.atm_project.exception.InvalidAmountException;
import com.atm_project.model.WithdrawalRequest;
import com.atm_project.model.WithdrawalResponse;
import com.atm_project.service.WithdrawalService;

@RestController
public class WithdrawalController {

    private WithdrawalService withdrawalService =
            new WithdrawalService();

    @PostMapping("/withdraw")
    public WithdrawalResponse withdraw(
            @RequestBody WithdrawalRequest request)
            		throws InvalidAmountException,
            	       InsufficientBalanceException,
            	       UserNotFoundException,
            	       Exception{

        double updatedBalance =
                withdrawalService.withdraw(
                        request.getAccountId(),
                        request.getAmount()
                );

        WithdrawalResponse response =
                new WithdrawalResponse(
                        "Amount withdrawn successfully",
                        updatedBalance
                );

        return response;
    }
}
