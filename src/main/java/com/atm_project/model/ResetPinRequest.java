package com.atm_project.model;

public class ResetPinRequest {

    private String cardNumber;
    private String newPin;
    private String confirmPin;


    public ResetPinRequest() {
    }


    public String getCardNumber() {
        return cardNumber;
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public String getNewPin() {
        return newPin;
    }


    public void setNewPin(String newPin) {
        this.newPin = newPin;
    }


    public String getConfirmPin() {
        return confirmPin;
    }


    public void setConfirmPin(String confirmPin) {
        this.confirmPin = confirmPin;
    }

}
