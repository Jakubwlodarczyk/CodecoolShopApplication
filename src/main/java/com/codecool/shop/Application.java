package com.codecool.shop;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.TablesCreator;
import static spark.Spark.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    private static Application app;
    private Connection connection;
    private ProductController productController = new ProductController();
    private BasketController basketController = new BasketController();
    private TablesCreator tablesCreator = new TablesCreator();

    private Application() {
    }

    private void setConnection() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
    }

    public void initializeTables() throws SQLException {
        try {
            tablesCreator.dropTables();
            tablesCreator.createTables();
            tablesCreator.seedUpTablesWithDumpData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void migrateTables() throws SQLException {
        try {
            tablesCreator.createTables();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }

    private void dispatchRoutes() {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);
        get("/", (req, res) -> this.productController.renderListProducts(req, res));
        get ("/basket", (req, res) -> this.basketController.renderListBasketItems(req, res));
        post("/byCategory", (req, res) -> this.productController.renderListProductByCategory(req,res));
        post("/bySupplier", (req, res) -> this.productController.renderListProductsBySupplier(req,res));
        post("/add-to-basket", (req, res) -> this.basketController.addToBasket(req, res));
        post("/delete-from-basket", (req, res) -> this.basketController.deleteFromBasket(req, res));
    }

    public static Application getApplication() {
        if(app == null) {
            app = new Application();
        }
        return app;
    }

    public void run() {
        try {
            this.setConnection();
            this.dispatchRoutes();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    System.out.println("Bye bye :( ");
                }
            });
        } catch (SQLException e) {
            System.out.println("Application initialization failed");
            e.printStackTrace();
        }
    }

    public static void stopApplicationBoot() {
        System.out.println("Database file not found, run this application with '--init-db' arguments");
        System.exit(0);
    }
}