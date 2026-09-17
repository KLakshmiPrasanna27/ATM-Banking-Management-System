
package com.atm_project.service;

import com.atm_project.dao.ActivityDAO;
import com.atm_project.dao.UserDAO;
import com.atm_project.exception.InvalidPinException;
import com.atm_project.exception.UserNotFoundException;

import com.atm_project.model.LoginResponse;
import com.atm_project.model.User;

public class UserService {

    private UserDAO userDAO =
            new UserDAO();

    private ActivityDAO activityDAO =
            new ActivityDAO();


    public LoginResponse login(
            String cardNumber,
            String pin)
            throws UserNotFoundException,
                   InvalidPinException {

        User user =
                userDAO.findByCardNumber(cardNumber);


        // Card number not found
        if (user == null) {

            try {

                activityDAO.saveActivity(
                        null,
                        "LOGIN",
                        "FAILED",
                        "Invalid card number",
                        null
                );

            } catch (Exception e) {

                System.out.println(
                        "Activity logging failed: "
                        + e.getMessage()
                );
            }

            throw new UserNotFoundException(
                    "User not found"
            );
        }


        // PIN is incorrect
        if (!user.getPin().equals(pin)) {

            try {

                activityDAO.saveActivity(
                        user.getAccountId(),
                        "LOGIN",
                        "FAILED",
                        "Invalid PIN",
                        null
                );

            } catch (Exception e) {

                System.out.println(
                        "Activity logging failed: "
                        + e.getMessage()
                );
            }

            throw new InvalidPinException(
                    "Invalid PIN"
            );
        }


        // Login successful
        try {

            activityDAO.saveActivity(
                    user.getAccountId(),
                    "LOGIN",
                    "SUCCESS",
                    "Login successful",
                    null
            );

        } catch (Exception e) {

            System.out.println(
                    "Activity logging failed: "
                    + e.getMessage()
            );
        }


        LoginResponse response =
                new LoginResponse(
                        user.getAccountId(),
                        user.getCustomerName(),
                        user.getCardNumber(),
                        user.getBalance()
                );

        return response;
    }
}

