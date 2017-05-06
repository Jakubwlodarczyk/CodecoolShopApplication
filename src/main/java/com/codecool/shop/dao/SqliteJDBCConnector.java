package com.codecool.shop.dao;

import java.sql.*;

public class SqliteJDBCConnector {

    public static Connection connection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void createTables() throws SQLException {
        Connection connection = connection();
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS \"products\" (\n" +
                "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`name`\tTEXT,\n" +
                "\t`description`\tTEXT,\n" +
                "\t`price`\tDOUBLE DEFAULT 0.00\n" +
                ")");

        statement.execute("CREATE TABLE IF NOT EXISTS \"categories\" (\n" +
                "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`name`\tTEXT NOT NULL,\n" +
                "\t`description`\tTEXT NOT NULL,\n" +
                "\t`department`\tTEXT NOT NULL\n" +
                ")");

        statement.execute("CREATE TABLE IF NOT EXISTS \"suppliers\" (\n" +
                "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`name`\tTEXT NOT NULL,\n" +
                "\t`description`\tTEXT NOT NULL\n" +
                ")");
    }
}
