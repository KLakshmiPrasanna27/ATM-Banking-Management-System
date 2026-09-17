package com.atm_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.atm_project.model.User;
import com.atm_project.util.DBUtil;
import java.util.ArrayList;
import java.util.List;

import com.atm_project.model.Transaction;

public class UserDAO {

    // =====================================================
    // 1. LOGIN
    // Finds user using card number
    // =====================================================

    public User findByCardNumber(String cardNumber) {

        String sql =
                "SELECT account_id, customer_name, card_number, pin, balance "
              + "FROM users WHERE card_number = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, cardNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setAccountId(
                        rs.getInt("account_id")
                );

                user.setCustomerName(
                        rs.getString("customer_name")
                );

                user.setCardNumber(
                        rs.getString("card_number")
                );

                user.setPin(
                        rs.getString("pin")
                );

                user.setBalance(
                        rs.getDouble("balance")
                );

                return user;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // 2. BALANCE ENQUIRY
    // Finds user using account ID
    // =====================================================

    public User findByAccountId(int accountId) {

        String sql =
                "SELECT account_id, customer_name, card_number, pin, balance "
              + "FROM users WHERE account_id = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setAccountId(
                        rs.getInt("account_id")
                );

                user.setCustomerName(
                        rs.getString("customer_name")
                );

                user.setCardNumber(
                        rs.getString("card_number")
                );

                user.setPin(
                        rs.getString("pin")
                );

                user.setBalance(
                        rs.getDouble("balance")
                );

                return user;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // 3. DEPOSIT
    // Updates balance AND stores transaction
    // using JDBC transaction management
    // =====================================================

    public double deposit(
            int accountId,
            double amount)
            throws Exception {

        Connection con = null;

        try {

            // Step 1: Create database connection
            con = DBUtil.getConnection();

            // Step 2: Start JDBC transaction
            con.setAutoCommit(false);


            // -------------------------------------------------
            // Step 3: Update account balance
            // -------------------------------------------------

            String updateBalanceSql =
                    "UPDATE users "
                  + "SET balance = balance + ? "
                  + "WHERE account_id = ?";

            PreparedStatement updatePs =
                    con.prepareStatement(
                            updateBalanceSql);

            updatePs.setDouble(1, amount);
            updatePs.setInt(2, accountId);

            int rowsUpdated =
                    updatePs.executeUpdate();


            // If account doesn't exist
            if (rowsUpdated == 0) {

                throw new Exception(
                        "Account not found");
            }


            // -------------------------------------------------
            // Step 4: Insert transaction history
            // -------------------------------------------------

            String insertTransactionSql =
                    "INSERT INTO transactions "
                  + "(account_id, transaction_type, amount) "
                  + "VALUES (?, ?, ?)";

            PreparedStatement insertPs =
                    con.prepareStatement(
                            insertTransactionSql);

            insertPs.setInt(1, accountId);

            insertPs.setString(
                    2,
                    "DEPOSIT"
            );

            insertPs.setDouble(
                    3,
                    amount
            );

            insertPs.executeUpdate();


            // -------------------------------------------------
            // Step 5: Get updated balance
            // -------------------------------------------------

            String balanceSql =
                    "SELECT balance "
                  + "FROM users "
                  + "WHERE account_id = ?";

            PreparedStatement balancePs =
                    con.prepareStatement(
                            balanceSql);

            balancePs.setInt(1, accountId);

            ResultSet rs =
                    balancePs.executeQuery();

            double updatedBalance = 0;

            if (rs.next()) {

                updatedBalance =
                        rs.getDouble("balance");
            }


            // -------------------------------------------------
            // Step 6: Commit transaction
            // -------------------------------------------------

            con.commit();

            return updatedBalance;


        } catch (Exception e) {

            // -------------------------------------------------
            // If anything fails, undo all changes
            // -------------------------------------------------

            if (con != null) {

                con.rollback();
            }

            throw e;


        } finally {

            // -------------------------------------------------
            // Close database connection
            // -------------------------------------------------

            if (con != null) {

                con.close();
            }
        }
    }
    public double withdraw(
            int accountId,
            double amount)
            throws Exception {

        Connection con = null;

        try {

            // Step 1: Create database connection
            con = DBUtil.getConnection();

            // Step 2: Start JDBC transaction
            con.setAutoCommit(false);


            // -------------------------------------------------
            // Step 3: Check current balance
            // -------------------------------------------------

            String balanceSql =
                    "SELECT balance "
                  + "FROM users "
                  + "WHERE account_id = ?";

            PreparedStatement balancePs =
                    con.prepareStatement(balanceSql);

            balancePs.setInt(1, accountId);

            ResultSet rs =
                    balancePs.executeQuery();


            if (!rs.next()) {

                throw new Exception(
                        "Account not found");
            }


            double currentBalance =
                    rs.getDouble("balance");


            // -------------------------------------------------
            // Step 4: Check sufficient balance
            // -------------------------------------------------

            if (currentBalance < amount) {

                throw new Exception(
                        "Insufficient balance");
            }


            // -------------------------------------------------
            // Step 5: Update account balance
            // -------------------------------------------------

            String updateBalanceSql =
                    "UPDATE users "
                  + "SET balance = balance - ? "
                  + "WHERE account_id = ?";

            PreparedStatement updatePs =
                    con.prepareStatement(
                            updateBalanceSql);

            updatePs.setDouble(1, amount);
            updatePs.setInt(2, accountId);

            updatePs.executeUpdate();


            // -------------------------------------------------
            // Step 6: Insert withdrawal transaction
            // -------------------------------------------------

            String insertTransactionSql =
                    "INSERT INTO transactions "
                  + "(account_id, transaction_type, amount) "
                  + "VALUES (?, ?, ?)";

            PreparedStatement insertPs =
                    con.prepareStatement(
                            insertTransactionSql);

            insertPs.setInt(1, accountId);

            insertPs.setString(
                    2,
                    "WITHDRAW"
            );

            insertPs.setDouble(
                    3,
                    amount
            );

            insertPs.executeUpdate();


            // -------------------------------------------------
            // Step 7: Get updated balance
            // -------------------------------------------------

            String updatedBalanceSql =
                    "SELECT balance "
                  + "FROM users "
                  + "WHERE account_id = ?";

            PreparedStatement updatedBalancePs =
                    con.prepareStatement(
                            updatedBalanceSql);

            updatedBalancePs.setInt(
                    1,
                    accountId
            );

            ResultSet updatedRs =
                    updatedBalancePs.executeQuery();


            double updatedBalance = 0;

            if (updatedRs.next()) {

                updatedBalance =
                        updatedRs.getDouble("balance");
            }


            // -------------------------------------------------
            // Step 8: Commit transaction
            // -------------------------------------------------

            con.commit();

            return updatedBalance;


        } catch (Exception e) {

            // If anything fails,
            // undo all database changes

            if (con != null) {

                con.rollback();
            }

            throw e;


        } finally {

            // Close database connection

            if (con != null) {

                con.close();
            }
        }
    }
    public List<Transaction> findTransactionsByAccountId(
            int accountId) throws Exception {

        List<Transaction> transactions =
                new ArrayList<>();

        Connection con = null;

        try {

            con = DBUtil.getConnection();

            String sql =
                    "SELECT transaction_id, "
                  + "account_id, "
                  + "transaction_type, "
                  + "amount, "
                  + "transaction_date "
                  + "FROM transactions "
                  + "WHERE account_id = ? "
                  + "ORDER BY transaction_date DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, accountId);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Transaction transaction =
                        new Transaction();

                transaction.setTransactionId(
                        rs.getInt("transaction_id")
                );

                transaction.setAccountId(
                        rs.getInt("account_id")
                );

                transaction.setTransactionType(
                        rs.getString("transaction_type")
                );

                transaction.setAmount(
                        rs.getDouble("amount")
                );

                transaction.setTransactionDate(
                        rs.getTimestamp("transaction_date")
                );

                transactions.add(transaction);
            }

            return transactions;

        } finally {

            if (con != null) {

                con.close();
            }
        }
    }
    public boolean changePin(
            int accountId,
            String oldPin,
            String newPin)
            throws Exception {

        Connection con = null;

        try {

            con = DBUtil.getConnection();

            String checkPinSql =
                    "SELECT pin "
                  + "FROM users "
                  + "WHERE account_id = ?";

            PreparedStatement checkPinPs =
                    con.prepareStatement(checkPinSql);

            checkPinPs.setInt(1, accountId);

            ResultSet rs =
                    checkPinPs.executeQuery();

            if (!rs.next()) {

                throw new Exception(
                        "Account not found"
                );
            }

            String currentPin =
                    rs.getString("pin");

            if (!currentPin.equals(oldPin)) {

                throw new Exception(
                        "Invalid old PIN"
                );
            }

            String updatePinSql =
                    "UPDATE users "
                  + "SET pin = ? "
                  + "WHERE account_id = ?";

            PreparedStatement updatePinPs =
                    con.prepareStatement(updatePinSql);

            updatePinPs.setString(1, newPin);
            updatePinPs.setInt(2, accountId);

            int rowsUpdated =
                    updatePinPs.executeUpdate();

            if (rowsUpdated == 0) {

                throw new Exception(
                        "PIN could not be changed"
                );
            }

            return true;

        } finally {

            if (con != null) {

                con.close();
            }
        }
    }
    public User findSecurityDetailsByCardNumber(
            String cardNumber)
            throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = DBUtil.getConnection();

            String sql =
                    "SELECT account_id, customer_name, "
                  + "security_question, security_answer "
                  + "FROM users "
                  + "WHERE card_number = ?";

            ps = con.prepareStatement(sql);

            ps.setString(1, cardNumber);

            rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setAccountId(
                        rs.getInt("account_id")
                );

                user.setCustomerName(
                        rs.getString("customer_name")
                );

                /*
                 * We temporarily need these values
                 * for Forgot PIN verification.
                 */
                user.setSecurityQuestion(
                        rs.getString("security_question")
                );

                user.setSecurityAnswer(
                        rs.getString("security_answer")
                );

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
    public void resetPin(
            String cardNumber,
            String newPin)
            throws Exception {

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = DBUtil.getConnection();

            String sql =
                    "UPDATE users "
                  + "SET pin = ? "
                  + "WHERE card_number = ?";

            ps = con.prepareStatement(sql);

            ps.setString(1, newPin);
            ps.setString(2, cardNumber);

            int rowsUpdated =
                    ps.executeUpdate();

            if (rowsUpdated == 0) {

                throw new Exception(
                        "Card number not found"
                );
            }

        } finally {

            if (ps != null) {
                ps.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }
}