package com.atm_project.model;

public class RegisterResponse {

    private String message;
    private int accountId;
    private String customerName;
    private String cardNumber;

    public RegisterResponse() {
    }

    public RegisterResponse(
            String message,
            int accountId,
            String customerName,
            String cardNumber) {

        this.message = message;
        this.accountId = accountId;
        this.customerName = customerName;
        this.cardNumber = cardNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}