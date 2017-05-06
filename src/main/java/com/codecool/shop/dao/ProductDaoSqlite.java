package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoSqlite extends BaseDao implements ProductDao {

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

        try {
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM products");
            products = this.getProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM products WHERE supplier_id= ?");
            statement.setInt(1, supplier.getId());
            products = this.getProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM products WHERE category_id= ?");
            statement.setInt(1, productCategory.getId());
            products = this.getProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private List<Product> getProducts(PreparedStatement statement) throws SQLException {
        List<Product> products = new ArrayList<>();
        SupplierDao supplierDao = new SupplierDaoSqlite();
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            Product product = new Product(
                    resultSet.getString("name"),
                    resultSet.getFloat("price"),
                    "PLN",
                    resultSet.getString("description"),
                    productCategoryDao.find(resultSet.getInt("category_id")),
                    supplierDao.find(resultSet.getInt("supplier_id"))
            );
            product.setId(resultSet.getInt("id"));
            products.add(product);
        }
        return products;
    }
}
