package com.atm_project.model;

public class LoginResponse {

    private int accountId;
    private String customerName;
    private String cardNumber;
    private double balance;

    public LoginResponse() {
    }

    public LoginResponse(
            int accountId,
            String customerName,
            String cardNumber,
            double balance) {

        this.accountId = accountId;
        this.customerName = customerName;
        this.cardNumber = cardNumber;
        this.balance = balance;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
