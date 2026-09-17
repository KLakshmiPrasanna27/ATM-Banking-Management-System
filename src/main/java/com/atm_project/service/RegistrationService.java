
package com.atm_project.service;

import com.atm_project.dao.ActivityDAO;
import com.atm_project.dao.RegistrationDAO;
import com.atm_project.exception.InvalidAmountException;
import com.atm_project.model.User;

public class RegistrationService {

    private RegistrationDAO registrationDAO =
            new RegistrationDAO();

    private ActivityDAO activityDAO =
            new ActivityDAO();


    public User register(
            String customerName,
            String cardNumber,
            String pin,
            String confirmPin,
            double initialDeposit,
            String securityQuestion,
            String securityAnswer)
            throws Exception {


        // Validate customer name

        if (customerName == null ||
            customerName.trim().isEmpty()) {

            throw new Exception(
                    "Customer name is required"
            );
        }


        // Validate card number

        if (cardNumber == null ||
            cardNumber.length() != 16) {

            throw new Exception(
                    "Card number must contain exactly 16 digits"
            );
        }


        // Check duplicate card number

        if (registrationDAO.cardNumberExists(cardNumber)) {

            logActivity(
                    null,
                    "FAILED",
                    "Registration failed - Card number already exists",
                    null
            );

            throw new Exception(
                    "Card number already exists"
            );
        }


        // Validate PIN

        if (pin == null ||
            pin.length() != 4) {

            throw new Exception(
                    "PIN must contain exactly 4 digits"
            );
        }


        // Validate Confirm PIN

        if (confirmPin == null ||
            !pin.equals(confirmPin)) {

            throw new Exception(
                    "PIN and Confirm PIN do not match"
            );
        }


        // Validate initial deposit

        if (initialDeposit < 500) {

            throw new InvalidAmountException(
                    "Minimum initial deposit is ₹500"
            );
        }


        // Validate security question

        if (securityQuestion == null ||
            securityQuestion.trim().isEmpty()) {

            throw new Exception(
                    "Security question is required"
            );
        }


        // Validate security answer

        if (securityAnswer == null ||
            securityAnswer.trim().isEmpty()) {

            throw new Exception(
                    "Security answer is required"
            );
        }


        try {

            User user =
                    registrationDAO.registerUser(
                            customerName,
                            cardNumber,
                            pin,
                            initialDeposit,
                            securityQuestion,
                            securityAnswer
                    );


            logActivity(
                    user.getAccountId(),
                    "SUCCESS",
                    "Account registered successfully",
                    initialDeposit
            );


            return user;


        } catch (Exception e) {

            logActivity(
                    null,
                    "FAILED",
                    "Registration failed due to a technical error",
                    initialDeposit
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
                    "REGISTRATION",
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
