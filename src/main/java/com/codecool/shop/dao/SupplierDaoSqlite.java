package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoSqlite extends BaseDao implements SupplierDao {
    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) throws SQLException {
        Supplier supplier = null;

        PreparedStatement statement = this.getConnection().prepareStatement("select * from suppliers WHERE id=?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            supplier = new Supplier(
                    rs.getString("name"),
                    rs.getString("description")
            );
            supplier.setId(rs.getInt("id"));
        }
        return supplier;
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();

        PreparedStatement statement = this.getConnection().prepareStatement("select * from suppliers");
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            Supplier supplier = new Supplier(
                    rs.getString("name"),
                    rs.getString("description")
            );
            supplier.setId(rs.getInt("id"));
            suppliers.add(supplier);
        }
        return suppliers;
    }
}
