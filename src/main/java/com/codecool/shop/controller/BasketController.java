package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;

import java.util.List;

/**
 * Created by krzysiek on 5/5/17.
 */
public class BasketController {
    ProductDao productDao = new ProductDaoSqlite();
    ProductView productView = new ProductView();


    public void addToCardAction() {
        List<Product> products = this.productDao.getAll();
        this.productView.displayProductsList(products);
        System.out.println("Select product by giving id");
        Integer productId = UserInput.getUserInput();
        Product product = productDao.find(productId);
        System.out.println(product);


    }
}
