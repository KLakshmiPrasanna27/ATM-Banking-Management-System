
package com.atm_project.service;

import com.atm_project.dao.ActivityDAO;
import com.atm_project.dao.UserDAO;
import com.atm_project.exception.InvalidAmountException;

public class DepositService {

    private UserDAO userDAO =
            new UserDAO();

    private ActivityDAO activityDAO =
            new ActivityDAO();


    public double deposit(
            int accountId,
            double amount)
            throws InvalidAmountException, Exception {


        // Minimum deposit limit
        if (amount < 100) {

            logActivity(
                    accountId,
                    "FAILED",
                    "Deposit failed - Minimum deposit amount is ₹100",
                    amount
            );

            throw new InvalidAmountException(
                    "Minimum deposit amount is ₹100"
            );
        }


        // Maximum deposit limit
        if (amount > 50000) {

            logActivity(
                    accountId,
                    "FAILED",
                    "Deposit failed - Maximum deposit amount per transaction is ₹50,000",
                    amount
            );

            throw new InvalidAmountException(
                    "Maximum deposit amount per transaction is ₹50,000"
            );
        }


        try {

            // Perform actual deposit
            double updatedBalance =
                    userDAO.deposit(
                            accountId,
                            amount
                    );


            // Deposit successful
            logActivity(
                    accountId,
                    "SUCCESS",
                    "Amount deposited successfully",
                    amount
            );


            return updatedBalance;


        } catch (Exception e) {

            // Technical/database failure
            logActivity(
                    accountId,
                    "FAILED",
                    "Deposit failed due to a technical error",
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
                    "DEPOSIT",
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

