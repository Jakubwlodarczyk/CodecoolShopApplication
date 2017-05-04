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
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private SupplierDao supplierDao = new SupplierDaoSqlite();
    private List<Product> products = productDao.getAll();
    private List<Supplier> suppliers = supplierDao.getAll();
    private List<ProductCategory> categories = productCategoryDao.getAll();
    static ProductView view = new ProductView();

    public void listProducts() {
        view.displayProductList(products);
        }

    public void listProductByCategory() {
        view.displayListProductByCategory(categories);
        Integer categoryId = UserInput.getUserInput();
        ProductCategory productCategory = productCategoryDao.find(categoryId);
        List<Product> products = productDao.getBy(productCategory);
        view.displayProductList(products);
    }

    public void listProductsBySupplier() {
        view.displayListProductBySupplier(suppliers);
        Integer supplierId = UserInput.getUserInput();
        Supplier supplier = supplierDao.find(supplierId);
        List<Product> products = productDao.getBy(supplier);
        view.displayProductList(products);
    }
}