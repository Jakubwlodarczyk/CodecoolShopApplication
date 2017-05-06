package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.BasketView;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;

import java.util.List;

public class BasketController {

    ProductDao productDao = new ProductDaoSqlite();
    ProductView view = new ProductView();
    Basket basket = new Basket();
    BasketView basketView = new BasketView();

    public void addToCartAction(){
      List<Product> products = this.productDao.getAll();
        this.view.displayProductList(products);
        System.out.println("Select product by giving it's id:");
        Integer productId = UserInput.getUserInput();
        Product product = productDao.find(productId);
        this.basket.add(product, 1);

    }

    public void displayCartAction() {
        basketView.displayBasketItems(this.basket.getItems());
    }
}
