package com.atm_project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atm_project.model.ChangePinRequest;
import com.atm_project.service.ChangePinService;

@RestController
public class ChangePinController {

    private ChangePinService changePinService =
            new ChangePinService();

    @PostMapping("/change-pin")
    public String changePin(
            @RequestBody ChangePinRequest request)
            throws Exception {

        changePinService.changePin(
                request.getAccountId(),
                request.getOldPin(),
                request.getNewPin()
        );

        return "PIN changed successfully";
    }
}