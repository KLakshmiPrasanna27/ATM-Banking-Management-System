package com.atm_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atm_project.model.ResetPinRequest;
import com.atm_project.model.SecurityQuestionResponse;
import com.atm_project.model.VerifySecurityAnswerRequest;
import com.atm_project.service.ForgotPinService;

@RestController
public class ForgotPinController {

    private ForgotPinService forgotPinService =
            new ForgotPinService();


    @GetMapping("/forgot-pin/question/{cardNumber}")
    public SecurityQuestionResponse getSecurityQuestion(
            @PathVariable String cardNumber)
            throws Exception {

        return forgotPinService.getSecurityQuestion(
                cardNumber
        );
    }


    @PostMapping("/forgot-pin/verify-answer")
    public boolean verifySecurityAnswer(
            @RequestBody VerifySecurityAnswerRequest request)
            throws Exception {

        return forgotPinService.verifySecurityAnswer(
                request.getCardNumber(),
                request.getSecurityAnswer()
        );
    }


    @PostMapping("/forgot-pin/reset")
    public String resetPin(
            @RequestBody ResetPinRequest request)
            throws Exception {

        forgotPinService.resetPin(
                request.getCardNumber(),
                request.getNewPin(),
                request.getConfirmPin()
        );

        return "PIN reset successfully";
    }

}