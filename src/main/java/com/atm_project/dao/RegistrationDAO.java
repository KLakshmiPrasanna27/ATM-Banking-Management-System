
package com.atm_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.atm_project.model.User;
import com.atm_project.util.DBUtil;

public class RegistrationDAO {

    public boolean cardNumberExists(
            String cardNumber)
            throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = DBUtil.getConnection();

            String sql =
                    "SELECT account_id "
                  + "FROM users "
                  + "WHERE card_number = ?";

            ps = con.prepareStatement(sql);

            ps.setString(1, cardNumber);

            rs = ps.executeQuery();

            return rs.next();

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


    public User registerUser(
            String customerName,
            String cardNumber,
            String pin,
            double initialDeposit,
            String securityQuestion,
            String securityAnswer)
            throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = DBUtil.getConnection();

            String sql =
                    "INSERT INTO users "
                  + "(customer_name, card_number, pin, "
                  + "balance, security_question, security_answer) "
                  + "VALUES (?, ?, ?, ?, ?, ?)";

            ps = con.prepareStatement(
                    sql,
                    java.sql.Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, customerName);
            ps.setString(2, cardNumber);
            ps.setString(3, pin);
            ps.setDouble(4, initialDeposit);
            ps.setString(5, securityQuestion);
            ps.setString(6, securityAnswer);

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {

                int accountId =
                        rs.getInt(1);

                User user =
                        new User();

                user.setAccountId(accountId);
                user.setCustomerName(customerName);
                user.setCardNumber(cardNumber);
                user.setPin(pin);
                user.setBalance(initialDeposit);

                return user;
            }

            return null;

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
