package com.codecool.shop.dao;

import org.apache.commons.io.FileUtils;
import java.io.*;
import java.sql.*;
import java.util.List;

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

    public static void createTables() throws SQLException, IOException {
        executeQueries(createListOfQueries("createCategoriesTableQuery"));
        executeQueries(createListOfQueries("createSuppliersTableQuery"));
        executeQueries(createListOfQueries("createProductsTableQuery"));
        System.out.println("Tables created!");
    }

    public static List<String> createListOfQueries(String fileName) throws SQLException, IOException {
        return FileUtils.readLines(new File("src/main/resources/public/data/" + fileName), "utf-8");
    }


    public static void dropTables() throws SQLException, IOException {
        executeQueries(createListOfQueries("dropTablesQuery"));
        System.out.println("Tables dropped!");
    }

    public static void seedUpTablesWithDumpData() throws SQLException, IOException {
        executeQueries(createListOfQueries("seedUpTablesWithDumpDataQuery"));
        System.out.println("Dump data applied!");
    }

    public static void executeQueries(List<String> queries) throws SQLException {
        Connection connection = connection();
        Statement statement = connection.createStatement();
        for (String line : queries) {
            statement.execute(line);
        }
    }
}
