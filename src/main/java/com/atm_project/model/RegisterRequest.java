
package com.atm_project.model;

public class RegisterRequest {

    private String customerName;
    private String cardNumber;
    private String pin;
    private String confirmPin;
    private double initialDeposit;

    private String securityQuestion;
    private String securityAnswer;


    public RegisterRequest() {
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


    public String getPin() {
        return pin;
    }


    public void setPin(String pin) {
        this.pin = pin;
    }


    public String getConfirmPin() {
        return confirmPin;
    }


    public void setConfirmPin(String confirmPin) {
        this.confirmPin = confirmPin;
    }


    public double getInitialDeposit() {
        return initialDeposit;
    }


    public void setInitialDeposit(double initialDeposit) {
        this.initialDeposit = initialDeposit;
    }


    public String getSecurityQuestion() {
        return securityQuestion;
    }


    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }


    public String getSecurityAnswer() {
        return securityAnswer;
    }


    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

}
