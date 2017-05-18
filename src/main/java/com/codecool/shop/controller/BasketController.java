package com.codecool.shop.controller;

import com.codecool.shop.model.Basket;
import com.codecool.shop.model.BasketItem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketController {

    public String renderListBasketItems(Request req, Response res) throws SQLException {
        Basket basket = req.session().attribute("basket");
        List<BasketItem> basketList = basket.getItems();
        Map<String, List> params = new HashMap<>();
        params.put("basketItems", basketList);
        return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/basket"));
    }
}
