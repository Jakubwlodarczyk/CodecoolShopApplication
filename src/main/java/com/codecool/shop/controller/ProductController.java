package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.*;
import spark.ModelAndView;
import spark.Response;
import spark.Request;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    public ProductController(){
        this.productDao = new ProductDaoSqlite(SqliteJDSCConnector.getConnection());
        this.productCategoryDao = new ProductCategoryDaoSqlite(SqliteJDSCConnector.getConnection());
        this.supplierDao = new SupplierDaoSqlite(SqliteJDSCConnector.getConnection());
    }

    public ModelAndView renderListProducts(Request req, Response res) throws SQLException {
        List<ProductCategory> categories = productCategoryDao.getAll();
        List<Supplier> suppliers = supplierDao.getAll();
        List<Product> products = productDao.getAll();
        Map<String, Object> params = new HashMap();

        params.put("products", products);
        params.put("categories", categories);
        params.put("suppliers", suppliers);

        if (req.session().attribute("basket") == null) {
            req.session().attribute("basket", new Basket());
        }

        if (req.session().attribute("product") != null && req.session().attribute("quantity") != null) {
            Product product = req.session().attribute("product");
            Integer quantity = req.session().attribute("quantity");
            req.session().removeAttribute("product");
            params.put("purchased", product);
            params.put("quantity", quantity);
        }
        return new ModelAndView(params, "product/index");
        }

    public ModelAndView renderListProductByCategory(Request req, Response res) throws SQLException {
        List<ProductCategory> categories = productCategoryDao.getAll();
        List<Supplier> suppliers = supplierDao.getAll();
        String categoryId = req.queryParams("selectCategory");
        ProductCategory category = productCategoryDao.find(Integer.parseInt(categoryId));
        List<Product> products = productDao.getBy(category);
        Map<String, Object> params = new HashMap();
        params.put("products", products);
        params.put("categories", categories);
        params.put("suppliers", suppliers);
        params.put("category", category.getName());
        return new ModelAndView(params, "product/index");
    }

    public ModelAndView renderListProductsBySupplier(Request req, Response res) throws SQLException {
        List<ProductCategory> categories = productCategoryDao.getAll();
        List<Supplier> suppliers = supplierDao.getAll();
        String supplierId = req.queryParams("selectSupplier");
        Supplier supplier = supplierDao.find(Integer.parseInt(supplierId));
        List<Product> products = productDao.getBy(supplier);
        Map<String, Object> params = new HashMap();
        params.put("products", products);
        params.put("suppliers", suppliers);
        params.put("categories", categories);
        params.put("supplier", supplier.getName());
        return new ModelAndView(params, "product/index");
    }
}