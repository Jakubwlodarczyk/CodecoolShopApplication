package com.codecool.shop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteJDSCConnector {
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(String driver) throws SQLException {

        connection = DriverManager.getConnection(driver);

    }
}
