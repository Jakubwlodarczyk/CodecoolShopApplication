package com.codecool.shop;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductController;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    private Connection connection;
    private ProductController productController = new ProductController();
    private BasketController basketController = new BasketController();

    public Application() {
        System.out.println("Application initialization in progress...");

        try {
            this.connectToDb();
            System.out.println("Connection established!");
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
        get ("/basket", (req, res) -> this.basketController.renderListBasketItems(req, res));
    }
}
