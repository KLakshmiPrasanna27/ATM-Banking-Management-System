
package com.atm_project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atm_project.dao.ActivityDAO;

@RestController
public class LogoutController {

    private ActivityDAO activityDAO =
            new ActivityDAO();


    @PostMapping("/logout")
    public String logout(
            @RequestParam int accountId) {

        try {

            activityDAO.saveActivity(
                    accountId,
                    "LOGOUT",
                    "SUCCESS",
                    "Logout successful",
                    null
            );

        } catch (Exception e) {

            System.out.println(
                    "Activity logging failed: "
                    + e.getMessage()
            );
        }

        return "Logout successful";
    }
}

