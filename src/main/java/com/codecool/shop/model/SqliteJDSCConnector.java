package com.codecool.shop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqliteJDSCConnector {
	private static Connection connection;

	public static void setConnection() {

		try {
			connection = DriverManager.getConnection(
					"jdbc:sqlite:src/main/resources/database.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(){
		return connection;
	}
}