package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoSqlite extends BaseDao implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) {
    }

    @Override
    public ProductCategory find(int id) throws SQLException{
        ProductCategory category = null;

        PreparedStatement statement = this.getConnection().prepareStatement("select * from categories WHERE id=?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            category = new ProductCategory(
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("department")
            );
            category.setId(rs.getInt("id"));
        }
        return category;
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<ProductCategory> getAll() throws SQLException {
        List<ProductCategory> categories = new ArrayList<>();

            PreparedStatement statement = this.getConnection().prepareStatement("select * from categories");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                ProductCategory category = new ProductCategory(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("department")
                        );
                category.setId(rs.getInt("id"));
                categories.add(category);
            }
        return categories;
    }
}
