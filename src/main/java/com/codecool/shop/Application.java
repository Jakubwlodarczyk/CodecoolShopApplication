package com.codecool.shop;


import com.codecool.shop.controller.MainMenuController;
import com.codecool.shop.dao.SqliteJDBCConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    private Connection connection;

    public Application() {
        System.out.println("Application initialization in progress...");

        try {
            this.connectToDb();
            this.dispatchMainMenuController();
        } catch (SQLException e) {
            System.out.println("Application initialization failed");
            e.printStackTrace();
        }
    }

    public void connectToDb() throws SQLException {
        System.out.println("Connecting to DB...");
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");

    }

    public void dispatchMainMenuController() {
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.mainMenuAction();
    }
}
