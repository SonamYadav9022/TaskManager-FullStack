package com.sonam.taskmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found!", e);
        }

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASS");

        // fallback for local development
        if (url == null) url = "jdbc:mysql://localhost:3306/taskdb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        if (user == null) user = "root";
        if (pass == null) pass = "Sonam@9022";

        System.out.println(">>> Connecting to: " + url);
        System.out.println(">>> User: " + user);

        return DriverManager.getConnection(url, user, pass);
    }
}


