package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.ProductView;


import java.util.List;

public class ProductController {

    private ProductDao productDao = new ProductDaoSqlite();
    private List<Product> products = productDao.getAll();

    public void listProducts() {
        ProductView view = new ProductView();
        view.displayProductList(products);
        }

}