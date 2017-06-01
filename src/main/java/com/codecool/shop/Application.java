package com.codecool.shop;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.dao.TablesCreator;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import com.codecool.shop.model.SqliteJDSCConnector;


import java.io.IOException;
import java.sql.SQLException;

import static spark.Spark.*;

public class Application {
	private static Application app;
	private ProductController productController;
	private BasketController basketController;
	private TablesCreator tablesCreator;

	private Application() {
		SqliteJDSCConnector.setConnection();
		this.productController = new ProductController(
				new ProductDaoSqlite(SqliteJDSCConnector.getConnection(),new SupplierDaoSqlite(SqliteJDSCConnector.getConnection()), new ProductCategoryDaoSqlite(SqliteJDSCConnector.getConnection())),
				new ProductCategoryDaoSqlite(SqliteJDSCConnector.getConnection()),
				new SupplierDaoSqlite(SqliteJDSCConnector.getConnection()));
		this.basketController = new BasketController();
		this.tablesCreator = new TablesCreator(SqliteJDSCConnector.getConnection());
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
		this.dispatchRoutes();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					SqliteJDSCConnector.getConnection().close();
					System.out.println("Connection with database closed.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Bye bye :( ");
			}
		});
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

}