package com.atm_project.atm;

import java.sql.Connection;
import com.atm_project.util.DBUtil;

public class DBConnectionTest {

    public static void main(String[] args) {

        try {

            Connection con = DBUtil.getConnection();

            System.out.println("Database Connected Successfully!");

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
