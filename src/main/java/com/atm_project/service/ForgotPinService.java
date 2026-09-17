package com.atm_project.service;

import com.atm_project.dao.UserDAO;
import com.atm_project.model.ForgotPinSession;
import com.atm_project.model.SecurityQuestionResponse;
import com.atm_project.model.User;

public class ForgotPinService {

    private UserDAO userDAO =
            new UserDAO();


    /*
     * Stores the card number after
     * successful security-answer verification.
     *
     * This is a simple temporary session
     * for our current project.
     */
    private ForgotPinSession forgotPinSession =
            new ForgotPinSession();


    // =====================================================
    // 1. GET SECURITY QUESTION
    // =====================================================

    public SecurityQuestionResponse
            getSecurityQuestion(
                    String cardNumber)
            throws Exception {

        if (cardNumber == null ||
            cardNumber.trim().isEmpty()) {

            throw new Exception(
                    "Card number is required"
            );
        }


        User user =
                userDAO.findSecurityDetailsByCardNumber(
                        cardNumber
                );


        if (user == null) {

            throw new Exception(
                    "Card number not found"
            );
        }


        return new SecurityQuestionResponse(
                user.getAccountId(),
                user.getCustomerName(),
                user.getSecurityQuestion()
        );
    }


    // =====================================================
    // 2. VERIFY SECURITY ANSWER
    // =====================================================

    public boolean verifySecurityAnswer(
            String cardNumber,
            String securityAnswer)
            throws Exception {

        if (cardNumber == null ||
            cardNumber.trim().isEmpty()) {

            throw new Exception(
                    "Card number is required"
            );
        }


        if (securityAnswer == null ||
            securityAnswer.trim().isEmpty()) {

            throw new Exception(
                    "Security answer is required"
            );
        }


        User user =
                userDAO.findSecurityDetailsByCardNumber(
                        cardNumber
                );


        if (user == null) {

            throw new Exception(
                    "Card number not found"
            );
        }


        /*
         * Compare the answer entered by
         * the user with the answer stored
         * in the database.
         */

        if (user.getSecurityAnswer()
                .equalsIgnoreCase(
                        securityAnswer.trim()
                )) {


            /*
             * Security answer is correct.
             *
             * Remember this card number as
             * verified.
             */

            forgotPinSession =
                    new ForgotPinSession(
                            cardNumber,
                            true
                    );


            return true;
        }


        /*
         * Wrong answer.
         *
         * Remove any previous verification.
         */

        forgotPinSession =
                new ForgotPinSession(
                        null,
                        false
                );


        return false;
    }


    // =====================================================
    // 3. RESET PIN
    // =====================================================

    public void resetPin(
            String cardNumber,
            String newPin,
            String confirmPin)
            throws Exception {


        if (cardNumber == null ||
            cardNumber.trim().isEmpty()) {

            throw new Exception(
                    "Card number is required"
            );
        }


        /*
         * IMPORTANT:
         *
         * Before changing the PIN,
         * check whether the security answer
         * was successfully verified.
         */

        if (!forgotPinSession.isVerified()) {

            throw new Exception(
                    "Please verify your security answer first"
            );
        }


        /*
         * Make sure the verified card number
         * is the same card number that is
         * requesting the PIN reset.
         */

        if (!cardNumber.equals(
                forgotPinSession.getCardNumber())) {

            throw new Exception(
                    "Card verification does not match"
            );
        }


        // =================================================
        // Validate new PIN
        // =================================================

        if (newPin == null ||
            !newPin.matches("[0-9]{4}")) {

            throw new Exception(
                    "New PIN must contain exactly 4 digits"
            );
        }


        // =================================================
        // Validate confirm PIN
        // =================================================

        if (confirmPin == null ||
            !newPin.equals(confirmPin)) {

            throw new Exception(
                    "New PIN and Confirm PIN do not match"
            );
        }


        /*
         * Check whether the card still exists.
         */

        User user =
                userDAO.findSecurityDetailsByCardNumber(
                        cardNumber
                );


        if (user == null) {

            throw new Exception(
                    "Card number not found"
            );
        }


        /*
         * Finally update the PIN.
         */

        userDAO.resetPin(
                cardNumber,
                newPin
        );


        /*
         * IMPORTANT:
         *
         * Once the PIN has been successfully
         * changed, remove the verification.
         *
         * The same verification should not
         * be reused again.
         */

        forgotPinSession =
                new ForgotPinSession(
                        null,
                        false
                );
    }
}