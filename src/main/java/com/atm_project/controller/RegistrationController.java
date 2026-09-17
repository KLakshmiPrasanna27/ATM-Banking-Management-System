package com.atm_project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atm_project.model.RegisterRequest;
import com.atm_project.model.RegisterResponse;
import com.atm_project.model.User;
import com.atm_project.service.RegistrationService;

@RestController
public class RegistrationController {

    private RegistrationService registrationService =
            new RegistrationService();


    @PostMapping("/register")
    public RegisterResponse register(
            @RequestBody RegisterRequest request)
            throws Exception {


        User user =
                registrationService.register(
                        request.getCustomerName(),
                        request.getCardNumber(),
                        request.getPin(),
                        request.getConfirmPin(),
                        request.getInitialDeposit(),
                        request.getSecurityQuestion(),
                        request.getSecurityAnswer()
                );


        RegisterResponse response =
                new RegisterResponse(
                        "Account registered successfully",
                        user.getAccountId(),
                        user.getCustomerName(),
                        user.getCardNumber()
                );


        return response;
    }

}