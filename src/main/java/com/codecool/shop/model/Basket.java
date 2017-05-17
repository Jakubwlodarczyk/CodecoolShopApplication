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

    public List<BasketItem> getItems() {
        Supplier supplier = new Supplier("supplier", "desc");
        ProductCategory productCategory = new ProductCategory("name", "department", "descript");
        Product product = new Product("name", 12f, "PLN", "DESCTIPTION", productCategory, supplier);
        Product product2 = new Product("name2fdas", 12f, "PLN", "DESCTIPTION", productCategory, supplier);

        this.items.add(new BasketItem(product, 1));
        this.items.add(new BasketItem(product2, 2));
        this.items.add(new BasketItem(product, 3));
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
