package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoSqlite implements ProductDao {

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        ProductCategory category = new ProductCategory("Category", "Department", "Description");
        Supplier supplier = new Supplier("Supplier",  "Description");

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from products");
            while(rs.next()) {
                Product product = new Product(
                        rs.getString("name"),
                        rs.getFloat("price"),
                        "PLN",
                        rs.getString("description"),
                        category,
                        supplier
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            System.out.println(e.getMessage());
        }


        Product product1 = new Product("Product 1", 15.05f, "PLN", "Something", category, supplier);
        Product product2 = new Product("Product 2", 15.05f, "PLN", "SomethingIsUp", category, supplier);
        Product product3 = new Product("Product 3", 15.05f, "PLN", "SomethingIsWrong", category, supplier);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
