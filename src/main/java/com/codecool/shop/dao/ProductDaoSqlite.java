package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.SqliteJDSCConnector;
import com.codecool.shop.model.Supplier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoSqlite extends BaseDao implements ProductDao {
    private SupplierDao supplierDao;
    private ProductCategoryDao productCategoryDao;

    public ProductDaoSqlite(Connection connection, SupplierDaoSqlite supplierDao, ProductCategoryDaoSqlite productCategoryDao) {
        super(connection);
        this.supplierDao = supplierDao;
        this.productCategoryDao = productCategoryDao;
    }

    @Override
    public Product find(int id) throws SQLException {
        Product product = null;

        PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM products WHERE id= ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            product = new Product(
            resultSet.getString("name"),
            resultSet.getFloat("price"),
            "PLN",
            resultSet.getString("description"),
            this.productCategoryDao.find(resultSet.getInt("category_id")),
            this.supplierDao.find(resultSet.getInt("supplier_id"))
            );
            product.setId(resultSet.getInt("id"));
        }
        return product;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM products");
        List<Product> products = this.getProducts(statement);

        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) throws SQLException {
        PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM products WHERE supplier_id= ?");
        statement.setInt(1, supplier.getId());
        List<Product> products = this.getProducts(statement);

        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) throws SQLException {
        PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM products WHERE category_id= ?");
        statement.setInt(1, productCategory.getId());
        List<Product> products = this.getProducts(statement);

        return products;
    }

    private List<Product> getProducts(PreparedStatement statement) throws SQLException {
        List<Product> products = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            Product product = new Product(
                    resultSet.getString("name"),
                    resultSet.getFloat("price"),
                    "PLN",
                    resultSet.getString("description"),
                    this.productCategoryDao.find(resultSet.getInt("category_id")),
                    this.supplierDao.find(resultSet.getInt("supplier_id"))
            );
            product.setId(resultSet.getInt("id"));
            products.add(product);
        }
        return products;
    }
}
