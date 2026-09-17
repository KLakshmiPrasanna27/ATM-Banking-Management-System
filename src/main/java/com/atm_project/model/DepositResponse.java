package com.atm_project.model;

public class DepositResponse {

    private String message;
    private double updatedBalance;

    public DepositResponse() {
    }

    public DepositResponse(
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
