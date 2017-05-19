package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.BasketItem;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketController {
    private ProductDaoSqlite proDaoSql = new ProductDaoSqlite();

    public String renderListBasketItems(Request req, Response res) throws SQLException {
        Basket basket = req.session().attribute("basket");
        List<BasketItem> basketList = basket.getItems();
        Map<String, List> params = new HashMap<>();
        params.put("basketItems", basketList);
        return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/basket"));
    }


    public String addToBasket(Request req, Response res) throws SQLException {
        Integer id = Integer.parseInt(req.queryParams("id"));
        Integer quantity = Integer.parseInt(req.queryParams("quantity"));
        Product product = proDaoSql.find(id);
        Basket basket = req.session().attribute("basket");
        basket.add(product, quantity);
        req.session().attribute("product", product);
        req.session().attribute("quantity", quantity);
        res.redirect("/");
        return "";
    }

    public String deleteFromBasket(Request req, Response res) throws SQLException {
        Integer id = Integer.parseInt(req.queryParams("id"));
        Integer quantity = Integer.parseInt(req.queryParams("quantity"));
        Product product = proDaoSql.find(id);
        Basket basket = req.session().attribute("basket");
        basket.remove(product, quantity);
        res.redirect("/basket");
        return "";
    }
}
