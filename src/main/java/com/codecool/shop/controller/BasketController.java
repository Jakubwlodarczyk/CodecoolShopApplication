package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.BasketItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.BasketView;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketController {

    ProductDao productDao = new ProductDaoSqlite();
    ProductView view = new ProductView();
    Basket basket = new Basket();
    BasketView basketView = new BasketView();

    public String renderListBasketItems(Request req, Response res) throws SQLException {
        Basket basket = req.session().attribute("basket");
        List<BasketItem> basketList = basket.getItems();
        Map<String, List> params = new HashMap<>();
        params.put("basketItems", basketList);
        return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/basket"));
    }

    public void addToCartAction() throws SQLException{
      List<Product> products = this.productDao.getAll();
        this.view.displayProductList(products);
        System.out.println("Select product by giving it's id:");
        Integer productId = UserInput.getUserInput();
        Product product = productDao.find(productId);
        this.basket.add(product, 1);
    }

}
