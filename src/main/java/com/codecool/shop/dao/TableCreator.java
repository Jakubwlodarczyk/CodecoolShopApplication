package com.codecool.shop.dao;

import com.codecool.shop.Application;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.sql.*;
import java.util.List;

public class TableCreator {

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
        Connection connection = Application.getApplication().getConnection();
        Statement statement = connection.createStatement();
        for (String line : queries) {
            statement.execute(line);
        }
    }
}
