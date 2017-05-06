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
        Product product = null;
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
        SupplierDao supplierDao = new SupplierDaoSqlite();
        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id, name, price, description, category_id, supplier_id from products WHERE id="+ id);
            while(rs.next()) {
                product = new Product(
                        rs.getString("name"),
                        rs.getFloat("price"),
                        "PLN",
                        rs.getString("description"),
                        productCategoryDao.find(rs.getInt("category_id")),
                        supplierDao.find(rs.getInt("supplier_id"))
                );
                product.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return product;

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
            Connection connection = SqliteJDBCConnector.connection();
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
                product.setId(rs.getInt("id"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            System.out.println(e.getMessage());
        }

        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {

        List<Product> products = new ArrayList<>();
        Product product;
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();

        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id, name, price, description, category_id from products WHERE supplier_id=" + supplier.getId());

            while(rs.next()) {
                product = new Product(rs.getString("name"),
                        rs.getFloat("price"),
                        "PLN",
                        rs.getString("description"),
                        productCategoryDao.find(rs.getInt("category_id")),
                        supplier
                );
                product.setId(rs.getInt("id"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            System.out.println(e.getMessage());
        }
        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {

        List<Product> products = new ArrayList<>();
        Product product;
        SupplierDao supplierDao = new SupplierDaoSqlite();

        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id, name, price, description, supplier_id from products WHERE category_id=" + productCategory.getId());

            while(rs.next()) {
                product = new Product(
                        rs.getString("name"),
                        rs.getFloat("price"),
                        "PLN",
                        rs.getString("description"),
                        productCategory,
                        supplierDao.find(rs.getInt("id"))
                );
                product.setId(rs.getInt("id"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return products;
    }
}
