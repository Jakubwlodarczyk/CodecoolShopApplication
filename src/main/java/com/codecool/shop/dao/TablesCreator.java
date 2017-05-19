package com.codecool.shop.dao;
import com.codecool.shop.Application;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.sql.*;
import java.util.List;

public class TablesCreator {
    public void createTables() throws SQLException, IOException {
        executeQueries(createListOfQueries("createCategoriesTableQuery"));
        executeQueries(createListOfQueries("createSuppliersTableQuery"));
        executeQueries(createListOfQueries("createProductsTableQuery"));
        System.out.println("Tables created!");
    }
    private List<String> createListOfQueries(String fileName) throws SQLException, IOException {
        return FileUtils.readLines(new File("src/main/resources/public/data/" + fileName), "utf-8");
    }
    public void dropTables() throws SQLException, IOException {
        executeQueries(createListOfQueries("dropTablesQuery"));
        System.out.println("Tables dropped!");
    }
    public void seedUpTablesWithDumpData() throws SQLException, IOException {
        executeQueries(createListOfQueries("seedUpTablesWithDumpDataQuery"));
        System.out.println("Dump data applied!");
    }
    private void executeQueries(List<String> queries) throws SQLException {
        Connection connection = Application.getApplication().getConnection();
        Statement statement = connection.createStatement();
        for (String line : queries) {
            statement.execute(line);
            statement.close();
        }
    }
}