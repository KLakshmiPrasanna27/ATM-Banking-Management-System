package com.atm_project.model;

public class BalanceResponse {

    private int accountId;
    private String customerName;
    private double balance;

    public BalanceResponse() {
    }

    public BalanceResponse(
            int accountId,
            String customerName,
            double balance) {

        this.accountId = accountId;
        this.customerName = customerName;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}