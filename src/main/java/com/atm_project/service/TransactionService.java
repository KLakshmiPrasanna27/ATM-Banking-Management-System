package com.atm_project.service;

import java.util.List;

import com.atm_project.dao.UserDAO;
import com.atm_project.exception.UserNotFoundException;
import com.atm_project.model.Transaction;
import com.atm_project.model.User;

public class TransactionService {

    private UserDAO userDAO =
            new UserDAO();

    public List<Transaction> getTransactions(
            int accountId)
            throws UserNotFoundException, Exception {

        User user =
                userDAO.findByAccountId(accountId);

        if (user == null) {

            throw new UserNotFoundException(
                    "Account not found"
            );
        }

        List<Transaction> transactions =
                userDAO.findTransactionsByAccountId(
                        accountId
                );

        return transactions;
    }
}