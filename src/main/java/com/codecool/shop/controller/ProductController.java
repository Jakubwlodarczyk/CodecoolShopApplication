package com.codecool.shop.controller;

import com.codecool.shop.Application;
import com.codecool.shop.dao.*;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;
import spark.ModelAndView;
import spark.Response;
import spark.Request;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private SupplierDao supplierDao = new SupplierDaoSqlite();
    private ProductView view = new ProductView();

    private ProductDaoSqlite proDaoSql = new ProductDaoSqlite();


    public String renderListProducts(Request req, Response res) {
        Map<String, List> params = new HashMap<>();
        List<Product> products = productDao.getAll();
        params = new HashMap();
        params.put("products", products);
        if (req.session().attribute("basket") == null) {
            req.session().attribute("basket", new Basket());
            System.out.println("Basket established");
        }
        return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/index"));
        }

    public String addToBasket(Request req, Response res) {
        Integer id = Integer.parseInt(req.queryParams("id"));
        Integer quantity = Integer.parseInt(req.queryParams("quantity"));
        Product product = proDaoSql.find(id);
        Basket basket = req.session().attribute("basket");
        basket.add(product, quantity);
        System.out.println("Total quantity of items in basket: " + basket.getTotalCount());
        res.redirect("/");
        return "";
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