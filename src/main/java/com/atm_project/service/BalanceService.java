package com.atm_project.service;

import com.atm_project.dao.UserDAO;
import com.atm_project.exception.UserNotFoundException;
import com.atm_project.model.User;

public class BalanceService {

    private UserDAO userDAO = new UserDAO();

    public User getBalance(int accountId)
            throws UserNotFoundException {

        // Step 1: Find the user using account ID
        User user = userDAO.findByAccountId(accountId);

        // Step 2: Check whether account exists
        if (user == null) {

            throw new UserNotFoundException(
                    "Account not found"
            );
        }

        // Step 3: Return the user
        return user;
    }
}