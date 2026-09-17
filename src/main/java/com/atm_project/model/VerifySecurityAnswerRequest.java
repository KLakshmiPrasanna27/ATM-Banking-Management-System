package com.atm_project.model;

public class VerifySecurityAnswerRequest {

    private String cardNumber;
    private String securityAnswer;


    public VerifySecurityAnswerRequest() {
    }


    public String getCardNumber() {
        return cardNumber;
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public String getSecurityAnswer() {
        return securityAnswer;
    }


    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

}
