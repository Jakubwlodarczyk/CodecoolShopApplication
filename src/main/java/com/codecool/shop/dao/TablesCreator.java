package com.codecool.shop.dao;
import com.codecool.shop.Application;
import com.codecool.shop.model.SqliteJDSCConnector;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.sql.*;
import java.util.List;

public class TablesCreator {
    private Connection connection;

    public TablesCreator(Connection connection){
        this.connection = connection;
    }
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
        Statement statement = this.connection.createStatement();
        for (String line : queries) {
            statement.execute(line);
            statement.close();
        }
    }
}