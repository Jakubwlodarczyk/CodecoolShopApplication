package com.codecool.shop.view;

import com.codecool.shop.model.BasketItem;

import java.util.List;

public class BasketView {
    public void displayBasketItems(List<BasketItem> items) {

        if(!items.isEmpty()) {
            System.out.println("Your Basket:");
            for (BasketItem item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Your basket is empty. Just like you :)");
        }
    }
}
