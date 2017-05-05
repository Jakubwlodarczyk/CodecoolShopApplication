package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzysiek on 5/5/17.
 */
public class Basket {

    private List<BasketItem> items = new ArrayList<>();

    public void add(Product product, Integer quantity) {
        boolean productExists = false;
        for (BasketItem item : this.getItems()) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                productExists = true;
            }
        }
        if (!productExists) {
            BasketItem item = new BasketItem(product, quantity);
            items.add(item);
        }
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public Integer getTotalCount() {
        Integer count = 0;
        for (BasketItem item : items) {
            count += item.getQuantity();
        }
        return count;
    }
}
