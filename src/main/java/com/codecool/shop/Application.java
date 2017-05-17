package com.codecool.shop;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SQLiteErrorCode;
import com.codecool.shop.dao.SqliteJDBCConnector;
import static spark.Spark.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    private Connection connection;
    private ProductController productController = new ProductController();

    public Application(String[] args) throws SQLException {

        try {
            this.connectToDb();
            System.out.println("Connection established!");
            if(args.length>0){
                String dropArgument = "--init-db";
                String createTablesArgument = "--migrate-db";
                if (dropArgument.equals(args[0])) {
                    SqliteJDBCConnector.dropTables();
                    SqliteJDBCConnector.createTables();
                    SqliteJDBCConnector.seedUpTablesWithDumpData();
                } else if (createTablesArgument.equals(args[0])) {
                    SqliteJDBCConnector.createTables();
            }

            }
            this.dispatchRoutes();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void connectToDb() throws SQLException {
        System.out.println("Connecting to DB...");
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
    }

    public void dispatchRoutes() {

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/", (req, res) -> this.productController.renderListProducts(req, res));
        post("/byCategory", (req, res) -> this.productController.renderListProductByCategory(req,res));
        post("/bySupplier", (req, res) -> this.productController.renderListProductsBySupplier(req,res));
        post("/add-to-basket", (req, res) -> this.productController.addToBasket(req, res));

    }
}
