package com.atm_project.model;

public class WithdrawalRequest {

    private int accountId;
    private double amount;

    public WithdrawalRequest() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
