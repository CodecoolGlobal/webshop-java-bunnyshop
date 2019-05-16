package com.codecool.shop.database;

import java.sql.*;

public class dbCreator {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/bunnyshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "roland1938";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
            return DriverManager.getConnection(
                    DATABASE,
                    DB_USER,
                    DB_PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR: Connection error.");
            e.printStackTrace();
        }
        return null;
    }

}

