package com.codecool.shop.dao;

import com.codecool.shop.Application;
import java.sql.Connection;
import java.sql.SQLException;

abstract class BaseDao {

        private Connection connection;

        public BaseDao(Connection connection){
            this.connection = connection;
        }

        Connection getConnection(){
            return this.connection;
        }
    }
