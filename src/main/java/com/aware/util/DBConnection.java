package com.aware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class gives us a MySQL connection wherever we need it
public class DBConnection {

    // Reads from environment variables on Render
    // For local development, set these as system env vars OR replace with hardcoded values
    private static final String URL      = System.getenv("DB_URL")      != null
                                           ? System.getenv("DB_URL")
                                           : "jdbc:mysql://localhost:3306/aware_db?useSSL=false&serverTimezone=UTC";

    private static final String USER     = System.getenv("DB_USER")     != null
                                           ? System.getenv("DB_USER")
                                           : "root";

    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null
                                           ? System.getenv("DB_PASSWORD")
                                           : "your_password_here"; // <-- change this for local

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL driver not found!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
