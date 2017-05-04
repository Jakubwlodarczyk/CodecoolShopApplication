package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.ProductView;


import java.util.List;

public class ProductController {

    private ProductDao productDao = new ProductDaoSqlite();
    private List<Product> products = productDao.getAll();
    static ProductView view = new ProductView();

    public void listProducts() {
        System.out.println("Products list:");
        view.displayProductList(products);
        }

    public void listProductByCategory() {
        System.out.println("Products by category:");
    }
}