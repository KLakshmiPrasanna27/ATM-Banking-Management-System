
package com.atm_project.service;

import com.atm_project.dao.ActivityDAO;
import com.atm_project.dao.UserDAO;
import com.atm_project.exception.InsufficientBalanceException;
import com.atm_project.exception.InvalidAmountException;
import com.atm_project.exception.UserNotFoundException;
import com.atm_project.model.User;

public class WithdrawalService {

    private UserDAO userDAO =
            new UserDAO();

    private ActivityDAO activityDAO =
            new ActivityDAO();


    public double withdraw(
            int accountId,
            double amount)
            throws InvalidAmountException,
                   InsufficientBalanceException,
                   UserNotFoundException,
                   Exception {


        // Minimum withdrawal limit
        if (amount < 100) {

            logActivity(
                    accountId,
                    "FAILED",
                    "Withdrawal failed - Minimum withdrawal amount is ₹100",
                    amount
            );

            throw new InvalidAmountException(
                    "Minimum withdrawal amount is ₹100"
            );
        }


        // Find the account
        User user =
                userDAO.findByAccountId(accountId);

        if (user == null) {

            logActivity(
                    accountId,
                    "FAILED",
                    "Withdrawal failed - Account not found",
                    amount
            );

            throw new UserNotFoundException(
                    "Account not found"
            );
        }


        // Check balance BEFORE maximum limit
        if (user.getBalance() < amount) {

            logActivity(
                    accountId,
                    "FAILED",
                    "Withdrawal failed - Insufficient balance",
                    amount
            );

            throw new InsufficientBalanceException(
                    "Insufficient balance"
            );
        }


        // Maximum withdrawal limit
        if (amount > 20000) {

            logActivity(
                    accountId,
                    "FAILED",
                    "Withdrawal failed - Maximum withdrawal amount per transaction is ₹20,000",
                    amount
            );

            throw new InvalidAmountException(
                    "Maximum withdrawal amount per transaction is ₹20,000"
            );
        }


        try {

            // Perform actual withdrawal
            double updatedBalance =
                    userDAO.withdraw(
                            accountId,
                            amount
                    );


            // Withdrawal successful
            logActivity(
                    accountId,
                    "SUCCESS",
                    "Amount withdrawn successfully",
                    amount
            );


            return updatedBalance;


        } catch (Exception e) {

            // Technical/database failure
            logActivity(
                    accountId,
                    "FAILED",
                    "Withdrawal failed due to a technical error",
                    amount
            );

            throw e;
        }
    }


    private void logActivity(
            Integer accountId,
            String status,
            String description,
            Double amount) {

        try {

            activityDAO.saveActivity(
                    accountId,
                    "WITHDRAWAL",
                    status,
                    description,
                    amount
            );

        } catch (Exception e) {

            System.out.println(
                    "Activity logging failed: "
                    + e.getMessage()
            );
        }
    }
}

