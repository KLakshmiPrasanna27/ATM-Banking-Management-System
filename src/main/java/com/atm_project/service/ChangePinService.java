
package com.atm_project.service;

import com.atm_project.dao.ActivityDAO;
import com.atm_project.dao.UserDAO;
import com.atm_project.exception.InvalidAmountException;
import com.atm_project.exception.InvalidOldPinException;
import com.atm_project.exception.UserNotFoundException;
import com.atm_project.model.User;

public class ChangePinService {

    private UserDAO userDAO =
            new UserDAO();

    private ActivityDAO activityDAO =
            new ActivityDAO();


    public void changePin(
            int accountId,
            String oldPin,
            String newPin)
            throws InvalidOldPinException,
                   UserNotFoundException,
                   InvalidAmountException,
                   Exception {


        // Find account
        User user =
                userDAO.findByAccountId(accountId);


        // Account not found
        if (user == null) {

            logActivity(
                    accountId,
                    "FAILED",
                    "PIN change failed - Account not found",
                    null
            );

            throw new UserNotFoundException(
                    "Account not found"
            );
        }


        // Check old PIN
        if (!user.getPin().equals(oldPin)) {

            logActivity(
                    accountId,
                    "FAILED",
                    "PIN change failed - Invalid old PIN",
                    null
            );

            throw new InvalidOldPinException(
                    "Invalid old PIN"
            );
        }


        // Check new PIN
        if (newPin == null ||
            newPin.length() != 4) {

            logActivity(
                    accountId,
                    "FAILED",
                    "PIN change failed - New PIN must contain exactly 4 digits",
                    null
            );

            throw new InvalidAmountException(
                    "New PIN must contain exactly 4 digits"
            );
        }


        // Change PIN
        try {

            userDAO.changePin(
                    accountId,
                    oldPin,
                    newPin
            );


            // PIN change successful
            logActivity(
                    accountId,
                    "SUCCESS",
                    "PIN changed successfully",
                    null
            );


        } catch (Exception e) {

            // Technical/database failure
            logActivity(
                    accountId,
                    "FAILED",
                    "PIN change failed due to a technical error",
                    null
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
                    "PIN_CHANGE",
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

