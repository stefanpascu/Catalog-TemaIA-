package com;
//package Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection conn = null;
    private DatabaseConnection() {}
    public static Connection getDataBaseConnection()
    {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/PaoDB", "root", "parola");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("Connected to the database!");
        return conn;
    }
}
