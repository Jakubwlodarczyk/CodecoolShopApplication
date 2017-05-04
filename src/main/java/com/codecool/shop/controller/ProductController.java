package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDaoSqlite ProductCategoryDaoSqlite = new ProductCategoryDaoSqlite();

    private ProductView view = new ProductView();

    public void listProducts() {
        List<Product> products = productDao.getAll();
        view.displayProductsList(products);
    }

    public void listProductsByCategory() {
        ProductCategoryDaoSqlite productDaoSqlite = new ProductCategoryDaoSqlite();
        List<ProductCategory> categories = productDaoSqlite.getAll();
        this.view.displayCategoriesList(categories);

        Integer categoryId = UserInput.getUserInput();
        System.out.println("Selected category: " + Integer.toString(categoryId));
        ProductCategory category = ProductCategoryDaoSqlite.find(categoryId);
        System.out.println(category);
        List<Product> products = productDao.getBy(category);
        this.view.displayProductsList(products);

    }
}