package com.atm_project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL =
            System.getenv("DB_URL") != null
                    ? System.getenv("DB_URL")
                    : "jdbc:mysql://localhost:3306/atm_db";

    private static final String USER =
            System.getenv("DB_USER") != null
                    ? System.getenv("DB_USER")
                    : "root";

    private static final String PASSWORD =
            System.getenv("DB_PASSWORD") != null
                    ? System.getenv("DB_PASSWORD")
                    : "root";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}