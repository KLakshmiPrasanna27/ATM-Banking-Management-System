package com.atm_project.atm;

import com.atm_project.model.LoginResponse;
import com.atm_project.service.UserService;

public class UserServiceTest {

    public static void main(String[] args) {

        try {

            UserService service =
                    new UserService();

            LoginResponse user =
                    service.login(
                            "1234567890123456",
                            "1234"
                    );

            System.out.println(
                    "Welcome "
                    + user.getCustomerName()
            );

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }
}