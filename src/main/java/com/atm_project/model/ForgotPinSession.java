package com.atm_project.model;

public class ForgotPinSession {

    private String cardNumber;
    private boolean verified;

    public ForgotPinSession() {
    }

    public ForgotPinSession(
            String cardNumber,
            boolean verified) {

        this.cardNumber = cardNumber;
        this.verified = verified;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}