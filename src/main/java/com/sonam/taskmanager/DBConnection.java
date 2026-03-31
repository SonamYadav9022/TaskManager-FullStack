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
        String url = "jdbc:mysql://yamabiko.proxy.rlwy.net:26435/railway?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String user = "root";
        String pass = "BLZKcoOsVzSNYZDZFKMchowZmkDpnVlY";
        return DriverManager.getConnection(url, user, pass);
    }
}