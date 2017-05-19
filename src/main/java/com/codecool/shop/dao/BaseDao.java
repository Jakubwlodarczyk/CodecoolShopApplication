package com.codecool.shop.dao;

import com.codecool.shop.Application;
import java.sql.Connection;
import java.sql.SQLException;

abstract class BaseDao {

        private Connection connection;

        public Connection getConnection() throws SQLException {
            connection = Application.getApplication().getConnection();
            return connection;
        }
    }
