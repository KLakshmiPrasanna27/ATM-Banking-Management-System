
package com.atm_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.atm_project.model.AccountActivity;
import com.atm_project.util.DBUtil;

public class ActivityDAO {

    public void saveActivity(
            Integer accountId,
            String activityType,
            String status,
            String description,
            Double amount)
            throws Exception {

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = DBUtil.getConnection();

            String sql =
                    "INSERT INTO account_activity "
                  + "(account_id, activity_type, status, "
                  + "description, amount) "
                  + "VALUES (?, ?, ?, ?, ?)";

            ps = con.prepareStatement(sql);

            if (accountId == null) {
                ps.setNull(1, java.sql.Types.INTEGER);
            } else {
                ps.setInt(1, accountId);
            }

            ps.setString(2, activityType);
            ps.setString(3, status);
            ps.setString(4, description);

            if (amount == null) {
                ps.setNull(5, java.sql.Types.DOUBLE);
            } else {
                ps.setDouble(5, amount);
            }

            ps.executeUpdate();

        } finally {

            if (ps != null) {
                ps.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }


    public List<AccountActivity> findActivitiesByAccountId(
            int accountId)
            throws Exception {

        List<AccountActivity> activities =
                new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = DBUtil.getConnection();

            String sql =
                    "SELECT activity_id, "
                  + "account_id, "
                  + "activity_type, "
                  + "status, "
                  + "description, "
                  + "amount, "
                  + "activity_date "
                  + "FROM account_activity "
                  + "WHERE account_id = ? "
                  + "ORDER BY activity_date DESC";

            ps = con.prepareStatement(sql);

            ps.setInt(1, accountId);

            rs = ps.executeQuery();

            while (rs.next()) {

                AccountActivity activity =
                        new AccountActivity();

                activity.setActivityId(
                        rs.getInt("activity_id")
                );

                activity.setAccountId(
                        rs.getInt("account_id")
                );

                activity.setActivityType(
                        rs.getString("activity_type")
                );

                activity.setStatus(
                        rs.getString("status")
                );

                activity.setDescription(
                        rs.getString("description")
                );

                double amount =
                        rs.getDouble("amount");

                if (rs.wasNull()) {
                    activity.setAmount(null);
                } else {
                    activity.setAmount(amount);
                }

                activity.setActivityDate(
                        rs.getTimestamp("activity_date")
                );

                activities.add(activity);
            }

            return activities;

        } finally {

            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }
}

