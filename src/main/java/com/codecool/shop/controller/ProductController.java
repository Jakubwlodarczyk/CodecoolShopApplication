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
        List<ProductCategory> categories = productCategoryDao.getAll();
        List<Product> products = productDao.getAll();
        Map<String, Object> params = new HashMap();
        params.put("products", products);
        params.put("categories", categories);
        params.put("category", "All products");
        return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/index"));
        }

    public String renderListProductByCategory(Request req, Response res) {
        List<ProductCategory> categories = productCategoryDao.getAll();
        String categoryId = req.queryParams("selectCategory");
        ProductCategory productCategory = productCategoryDao.find(Integer.parseInt(categoryId));
        List<Product> products = productDao.getBy(productCategory);
        Map<String, Object> params = new HashMap();
        params.put("products", products);
        params.put("categories", categories);
        params.put("category", productCategory.getName());
        return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/index"));
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