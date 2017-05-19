package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

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
            this.getItems().add(item);
        }
    }

    public boolean remove(Product product, Integer quantity) {
        for (BasketItem item : this.getItems()) {
            if (item.getProduct().getId() == product.getId()) {
                if (quantity < item.getQuantity()) {
                    item.setQuantity(item.getQuantity() - quantity);
                    return true;
                    }
                if ( quantity.equals(item.getQuantity()) ) {
                    this.getItems().remove(item);
                    return true;
                }
                }
            }
        return false;
    }


    public List<BasketItem> getItems() {
        return this.items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer count = 0;
        for(BasketItem item : this.getItems()) {
            count += item.getQuantity();
        }
        return count;
    }
}
