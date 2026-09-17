package com.atm_project.atm;

import com.atm_project.dao.UserDAO;
import com.atm_project.model.User;

public class UserDAOTest {

    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        User user =
                dao.findByCardNumber("1234567890123456");

        if (user != null) {

            System.out.println(
                    "Customer Name: "
                    + user.getCustomerName()
            );

            System.out.println(
                    "Account ID: "
                    + user.getAccountId()
            );

            System.out.println(
                    "Balance: ₹"
                    + user.getBalance()
            );

        } else {

            System.out.println("User not found");
        }
    }
}
