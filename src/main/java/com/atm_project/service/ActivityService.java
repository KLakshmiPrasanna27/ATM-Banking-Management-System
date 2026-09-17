
package com.atm_project.service;

import java.util.List;

import com.atm_project.dao.ActivityDAO;
import com.atm_project.model.AccountActivity;

public class ActivityService {

    private ActivityDAO activityDAO =
            new ActivityDAO();


    public void logActivity(
            Integer accountId,
            String activityType,
            String status,
            String description,
            Double amount) {

        try {

            activityDAO.saveActivity(
                    accountId,
                    activityType,
                    status,
                    description,
                    amount
            );

        } catch (Exception e) {

            // Activity logging should not
            // stop the main ATM operation.
            System.out.println(
                    "Activity logging failed: "
                    + e.getMessage()
            );
        }
    }


    public List<AccountActivity> getActivities(
            int accountId)
            throws Exception {

        return activityDAO.findActivitiesByAccountId(
                accountId
        );
    }
}

