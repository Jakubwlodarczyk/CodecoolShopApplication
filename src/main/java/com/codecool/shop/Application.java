package com.codecool.shop;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;
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

    private Application() {
        try {
            this.getConnection();
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

    public void initializeTables() throws SQLException {
        try {
            SqliteJDBCConnector.dropTables();
            SqliteJDBCConnector.createTables();
            SqliteJDBCConnector.seedUpTablesWithDumpData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void migrateTables() throws SQLException {
        try {
            SqliteJDBCConnector.createTables();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
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
        post("/add-to-basket", (req, res) -> this.productController.addToBasket(req, res));

        post("/delete-from-basket", (req, res) -> this.productController.deleteFromBasket(req, res));

    }

    public static Application getApplication() {
        if(app == null) {
            app = new Application();
        }
        return app;
    }
}
