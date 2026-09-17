package com.atm_project.model;

public class User {

    private int accountId;
    private String customerName;
    private String cardNumber;
    private String pin;
    private double balance;

    private String securityQuestion;
    private String securityAnswer;


    // Default constructor
    public User() {
    }


    // Constructor
    public User(
            int accountId,
            String customerName,
            String cardNumber,
            String pin,
            double balance) {

        this.accountId = accountId;
        this.customerName = customerName;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }


    // Account ID
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    // Customer Name
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    // Card Number
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    // PIN
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }


    // Balance
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    // Security Question
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(
            String securityQuestion) {

        this.securityQuestion = securityQuestion;
    }


    // Security Answer
    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(
            String securityAnswer) {

        this.securityAnswer = securityAnswer;
    }

}