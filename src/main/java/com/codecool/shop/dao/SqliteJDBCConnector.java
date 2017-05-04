package com.codecool.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteJDBCConnector {

    public static Connection connection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        } catch (SQLException e) {
            System.out.println("Connection to DB failed");
            e.printStackTrace();
        }

        return connection;
    }

    public static void createTables() throws SQLException {
        Connection connection = connection();
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS products\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name VARCHAR NOT NULL,\n" +
                "    description TEXT,\n" +
                "    price DOUBLE DEFAULT 0.00 NOT NULL\n" +
                ")");



        statement.execute("CREATE TABLE IF NOT EXISTS products \n" +
                        "(\n" +
                "id          INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "name        VARCHAR NOT NULL, \n" +
                "description TEXT, \n" +
                "price       DOUBLE  DEFAULT 0 \n" +
                "NOT NULL, \n" +
                "category_id INTEGER REFERENCES categories (id) \n" +
                "NOT NULL)"
        );


//        CREATE TABLE categories (
//                id          INTEGER PRIMARY KEY
//                UNIQUE,
//                name        VARCHAR,
//                description TEXT,
//                department  VARCHAR,
//                category_id         NOT NULL
//        );






    }

}