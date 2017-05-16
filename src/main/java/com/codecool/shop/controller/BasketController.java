package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.BasketView;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketController {

    ProductDao productDao = new ProductDaoSqlite();
    ProductView view = new ProductView();
    Basket basket = new Basket();
    BasketView basketView = new BasketView();


    public String renderListProducts(Request req, Response res) {
        Map<String, List> params = new HashMap<>();
        params.put("products", products);
        return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/index"));
    }

    public void addToCartAction(){
      List<Product> products = this.productDao.getAll();
        this.view.displayProductList(products);

        System.out.println("Select product by giving it's id:");
        Integer productId = UserInput.getUserInput();
        Product product = productDao.find(productId);
        this.basket.add(product, 1);
    }



    public void displayCartAction() {
        this.basketView.displayBasketItems(this.basket.getItems());
    }
}
