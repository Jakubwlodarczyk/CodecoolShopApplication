package com.codecool.shop.view;

import com.codecool.shop.model.BasketItem;

import java.util.List;

/**
 * Created by krzysiek on 5/5/17.
 */
public class BasketView {

    public void displayBasketItems(List<BasketItem> items) {
        if(items.size() == 0) {
            System.out.println("Basket is empty");
        }
        for (BasketItem item : items) {
            System.out.println(item);
        }
    }

}
