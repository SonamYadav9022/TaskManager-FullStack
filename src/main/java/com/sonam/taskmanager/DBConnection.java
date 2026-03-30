package com.sonam.taskmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/taskdb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "Sonam@9022"; // ← change this

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found!", e);
        }
        // Read from environment variables (Railway will provide these)
        String url = System.getenv().getOrDefault("DB_URL",
                "jdbc:mysql://localhost:3306/taskdb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        String user = System.getenv().getOrDefault("DB_USER", "root");
        String pass = System.getenv().getOrDefault("DB_PASS", "Sonam@9022");
        return DriverManager.getConnection(url, user, pass);
    }
}