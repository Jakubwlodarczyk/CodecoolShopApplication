package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.BasketView;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;

import java.util.List;

/**
 * Created by krzysiek on 5/5/17.
 */
public class BasketController {
    ProductDao productDao = new ProductDaoSqlite();
    ProductView productView = new ProductView();
    BasketView basketView = new BasketView();
    Basket basket = new Basket();


    public void addToCardAction() {
        List<Product> products = this.productDao.getAll();
        this.productView.displayProductsList(products);

        System.out.println("Select product by giving id");
        Integer productId = UserInput.getUserInput();
        Product product = productDao.find(productId);
        System.out.println(this.basket.getTotalCount());
        this.basket.add(product, 1);
        System.out.println(this.basket.getTotalCount());
        this.basket.add(product, 5);
        System.out.println(this.basket.getTotalCount());
        this.basketView.displayBasketItems(this.basket.getItems());
    }

    public void displayCartAction() {
        this.basketView.displayBasketItems(this.basket.getItems());
    }
}
