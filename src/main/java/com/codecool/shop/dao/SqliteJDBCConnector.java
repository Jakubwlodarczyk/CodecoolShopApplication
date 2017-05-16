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

        statement.execute("CREATE TABLE IF NOT EXISTS`products` (\n" +
                "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`name`\tTEXT,\n" +
                "\t`description`\tTEXT,\n" +
                "\t`price`\tNUMERIC DEFAULT 0.00,\n" +
                "\t`category_id`\tINT,\n" +
                "\t`supplier_id`\tINT,\n" +
                "\tFOREIGN KEY(`category_id`) REFERENCES `suppliers`(`id`)\n" +
                ");");
        System.out.println("Tables created!");
    }

    public static void dropTables() throws SQLException {
        Connection connection = connection();
        Statement statement = connection.createStatement();
        statement.execute("PRAGMA writable_schema = 1;");
        statement.execute("delete from sqlite_master where type = 'table';");
        statement.execute("PRAGMA writable_schema = 0;");
        System.out.println("Tables dropped!");
    }

    public static void seedUpTablesWithDumpData() throws SQLException {
        Connection connection = connection();
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO products (name, description, price, category_id, supplier_id)" +
                "VALUES ('product1', 'description1', 10.00, 1, 1)");
        statement.execute("INSERT INTO products (name, description, price, category_id, supplier_id)" +
                "VALUES ('product2', 'description2', 20.00, 1, 1)");
        statement.execute("INSERT INTO products (name, description, price, category_id, supplier_id)" +
                "VALUES ('product3', 'description3', 30.00, 2, 2)");
        statement.execute("INSERT INTO products (name, description, price, category_id, supplier_id)" +
                "VALUES ('product4', 'description4', 40.00, 2, 2)");
        statement.execute("INSERT INTO products (name, description, price, category_id, supplier_id)" +
                "VALUES ('product5', 'description5', 50.00, 3, 3)");
        statement.execute("INSERT INTO products (name, description, price, category_id, supplier_id)" +
                "VALUES ('product6', 'description6', 60.00, 3, 3)");

        statement.execute("INSERT INTO categories (name, description, department)" +
                "VALUES ('category1', 'description1', 'department1')");
        statement.execute("INSERT INTO categories (name, description, department)" +
                "VALUES ('category2', 'description1', 'department2')");
        statement.execute("INSERT INTO categories (name, description, department)" +
                "VALUES ('category3', 'description1', 'department3')");

        statement.execute("INSERT INTO suppliers (name, description)" +
                "VALUES ('supplier1', 'description1')");
        statement.execute("INSERT INTO suppliers (name, description)" +
                "VALUES ('supplier2', 'description2')");
        statement.execute("INSERT INTO suppliers (name, description)" +
                "VALUES ('supplier3', 'description3')");

    }
}
