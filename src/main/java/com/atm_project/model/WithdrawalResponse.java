package com.atm_project.model;

public class WithdrawalResponse {

    private String message;
    private double updatedBalance;

    public WithdrawalResponse() {
    }

    public WithdrawalResponse(
            String message,
            double updatedBalance) {

        this.message = message;
        this.updatedBalance = updatedBalance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getUpdatedBalance() {
        return updatedBalance;
    }

    public void setUpdatedBalance(
            double updatedBalance) {

        this.updatedBalance = updatedBalance;
    }
}
