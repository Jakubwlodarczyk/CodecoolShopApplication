package com.codecool.shop.model;

/**
 * Created by krzysiek on 5/5/17.
 */
public class BasketItem extends BaseModel{
    private Product product;
    private Integer quantity;

    BasketItem(Product product, Integer quantity) {
        super("", "");
        this.setProduct(product);
        this.setQuantity(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
