package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDao {

    Supplier find(int id) throws SQLException;
    List<Supplier> getAll() throws SQLException;
}
