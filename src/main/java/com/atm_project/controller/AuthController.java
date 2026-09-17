package com.atm_project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atm_project.exception.InvalidPinException;
import com.atm_project.exception.UserNotFoundException;
import com.atm_project.model.LoginRequest;
import com.atm_project.model.LoginResponse;
import com.atm_project.service.UserService;

@RestController
public class AuthController {

    private UserService userService = new UserService();

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request)
            throws InvalidPinException, UserNotFoundException {

        return userService.login(
                request.getCardNumber(),
                request.getPin()
        );
    }
}