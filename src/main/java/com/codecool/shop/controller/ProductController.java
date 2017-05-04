package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
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

    public void listProductsBySupplier() {
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        List<Supplier> suppliers = supplierDaoSqlite.getAll();
        this.view.displaySuppliersList(suppliers);
        Integer supplierId = UserInput.getUserInput();
        System.out.println("Selected supplier: " + Integer.toString(supplierId));
        Supplier supplier = supplierDaoSqlite.find(supplierId);
        System.out.println(supplier);
        List<Product> products = productDao.getBy(supplier);
        this.view.displayProductsList(products);
    }

}