package com.codecool.shop;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;

import static spark.Spark.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    private Connection connection;
    private ProductController productController = new ProductController();
    private String dropArgument = "--init-db";
    private String createTablesArgument = "--migrate-db";

    public Application(String[] args) throws SQLException {

        try {
            this.connectToDb();
            System.out.println("Connection established!");
            if(args.length>0){
                if (dropArgument.equals(args[0])) {
                    SqliteJDBCConnector.dropTables();
                    SqliteJDBCConnector.createTables();
                    SqliteJDBCConnector.seedUpTablesWithDumpData();
                } else if (createTablesArgument.equals(args[0])) {
                    SqliteJDBCConnector.createTables();
            }

            }
            this.routs();


        } catch (SQLException e) {
            System.out.println("Application initialization failed");
            e.printStackTrace();
        }
    }

    public void connectToDb() throws SQLException {
        System.out.println("Connecting to DB...");
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
    }


    public void routs() {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/", (req, res) -> this.productController.renderListProducts(req, res));

    }
}
