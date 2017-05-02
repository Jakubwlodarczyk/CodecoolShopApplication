package com.codecool.shop.controller;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apolak on 02.05.17.
 */
public class ProductDaoSqlite {
    List<Product> products = new ArrayList<Product>();
    ProductCategory category = new ProductCategory(name: "Category", department: "Department", description: "Description");
    Supplier supplier = new Supplier(name: "Supplier", description: "Description");
    Product product1 = new Product(name: "Product 1", defaultPrice: 12.50f, currencyString: "PLN");
}
