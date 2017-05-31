package com.codecool.shop;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.TablesCreator;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static spark.Spark.*;

public class Application {
    private static Application app;
    private Connection connection;
    private ProductController productController = new ProductController();
    private BasketController basketController = new BasketController();
    private TablesCreator tablesCreator = new TablesCreator();

    private Application() {
    }

    public static Application getApplication() {
        if (app == null) {
            app = new Application();
        }
        return app;
    }

    public static void stopApplicationBoot() {
        System.out.println("Database file not found, run this application with '--init-db' arguments");
        System.exit(0);
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
        get("/", this.productController::renderListProducts, new ThymeleafTemplateEngine());
        get("/basket", this.basketController::renderListBasketItems, new ThymeleafTemplateEngine());
        post("/byCategory",this.productController::renderListProductByCategory,new ThymeleafTemplateEngine());
        post("/bySupplier",this.productController::renderListProductsBySupplier,new ThymeleafTemplateEngine());
        post("/add-to-basket", (req, res) -> this.basketController.addToBasket(req, res));
        post("/delete-from-basket", (req, res) -> this.basketController.deleteFromBasket(req, res));
    }

    public void run() {
        try {
            this.setConnection();
            this.dispatchRoutes();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        connection.close();
                        System.out.println("Connection with database closed.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Bye bye :( ");
                }
            });
        } catch (SQLException e) {
            System.out.println("Application initialization failed");
            e.printStackTrace();
        }
    }
}