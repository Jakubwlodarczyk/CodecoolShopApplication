package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzysiek on 5/4/17.
 */
public class SupplierDaoSqlite implements SupplierDao {
    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        Supplier supplier = null;
        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from suppliers where id = " + Integer.toString(id));
            if (!rs.next()) {
                return supplier;
            } else {
                supplier = new Supplier(
                        rs.getString("name"),
                        rs.getString("description"));
                supplier.setId(rs.getInt("id"));
            }

        }   catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return supplier;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<Supplier>();
        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from suppliers");
            while(rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getString("name"),
                        rs.getString("description")
                );
                supplier.setId(rs.getInt("id"));
                suppliers.add(supplier);
            }
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return suppliers;
    }
}
