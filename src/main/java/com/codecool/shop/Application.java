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
    private static Application app = null;
    private Connection connection;
    private ProductController productController = new ProductController();
    private BasketController basketController = new BasketController();

    private Application(String[] args) throws SQLException{

        try {
            this.connectToDb();
            System.out.println("Connection established!");
            if(args.length>0){
                if (args[0].equals("--init-db")) {
                    SqliteJDBCConnector.dropTables();
                    SqliteJDBCConnector.createTables();
                    SqliteJDBCConnector.seedUpTablesWithDumpData();
                } else if (args[0].equals("--migrate-db")) {
                    SqliteJDBCConnector.createTables();
                }
            }
            this.dispatchRoutes();

        } catch (SQLException e) {
            System.out.println("Application initialization failed");
            e.printStackTrace();

        } catch (IOException e) {
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
        get ("/basket", (req, res) -> this.basketController.renderListBasketItems(req, res));
        post("/byCategory", (req, res) -> this.productController.renderListProductByCategory(req,res));
        post("/bySupplier", (req, res) -> this.productController.renderListProductsBySupplier(req,res));
        post("/add-to-basket", (req, res) -> this.productController.addToBasket(req, res));

        post("/delete-from-basket", (req, res) -> this.productController.deleteFromBasket(req, res));

    }
    public static Application getApplication(String[] args) throws SQLException {
        if(app == null) {
            app = new Application(args);
        }
        return app;
    }
}
