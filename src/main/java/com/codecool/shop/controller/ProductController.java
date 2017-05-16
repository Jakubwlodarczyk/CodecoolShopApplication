package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;
import spark.ModelAndView;
import spark.Response;
import spark.Request;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private SupplierDao supplierDao = new SupplierDaoSqlite();
    private ProductView view = new ProductView();

    public String renderListProducts(Request req, Response res) {
        Map<String, List> params = new HashMap<>();
        List<Product> products = productDao.getAll();
        params.put("products", products);
        return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/index"));
        }

    public void listProductByCategory() {
        List<ProductCategory> categories = productCategoryDao.getAll();
        this.view.displayListProductByCategory(categories);

        Integer categoryId = UserInput.getUserInput();
        ProductCategory productCategory = productCategoryDao.find(categoryId);
        List<Product> products = productDao.getBy(productCategory);
        this.view.displayProductList(products);
    }

    public void listProductsBySupplier() {
        List<Supplier> suppliers = supplierDao.getAll();
        this.view.displayListProductBySupplier(suppliers);

        Integer supplierId = UserInput.getUserInput();
        Supplier supplier = supplierDao.find(supplierId);
        List<Product> products = productDao.getBy(supplier);
        this.view.displayProductList(products);
    }
}