package com.atm_project.model;

public class SecurityQuestionResponse {

    private int accountId;
    private String customerName;
    private String securityQuestion;

    public SecurityQuestionResponse() {
    }

    public SecurityQuestionResponse(
            int accountId,
            String customerName,
            String securityQuestion) {

        this.accountId = accountId;
        this.customerName = customerName;
        this.securityQuestion = securityQuestion;
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

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }
}
