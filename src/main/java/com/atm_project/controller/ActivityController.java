
package com.atm_project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atm_project.model.AccountActivity;
import com.atm_project.service.ActivityService;

@RestController
public class ActivityController {

    private ActivityService activityService =
            new ActivityService();


    @GetMapping("/activities/{accountId}")
    public List<AccountActivity> getActivities(
            @PathVariable int accountId)
            throws Exception {

        return activityService.getActivities(
                accountId
        );
    }
}
