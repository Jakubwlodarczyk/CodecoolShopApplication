package com.codecool.shop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqliteJDSCConnector {

	public Connection connection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(
					"jdbc:sqlite:src/main/resources/database.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
