package com.codecool.shop.view;

import com.codecool.shop.model.Product;

import java.util.List;

public class ProductView {
    public void displayProductList(List<Product> products) {
        for(Product p: products) {
            System.out.println(p.getName());

        }
    }
}
