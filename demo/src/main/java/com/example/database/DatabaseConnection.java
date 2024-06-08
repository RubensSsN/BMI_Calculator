package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:h2:mem:bmi_db";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.createStatement().execute("CREATE TABLE BMI_RECORDS (ID INT AUTO_INCREMENT PRIMARY KEY, HEIGHT DOUBLE, WEIGHT DOUBLE, BMI DOUBLE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
