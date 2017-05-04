package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.view.ProductView;


import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private List<Product> products = productDao.getAll();
    private List<ProductCategory> categories = productCategoryDao.getAll();
    static ProductView view = new ProductView();

    public void listProducts() {
        view.displayProductList(products);
        }

    public void listProductByCategory() {
        view.displayListProductByCategory(categories);



    }
}