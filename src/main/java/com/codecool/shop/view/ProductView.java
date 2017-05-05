package com.codecool.shop.view;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class ProductView {
    public void displayProductList(List<Product> products) {
        System.out.println("Products list:");
        for(Product p: products) {
            System.out.println(p);
        }
    }

    public void displayListProductByCategory(List<ProductCategory> categories) {
        System.out.println("Products by category:");
        for(ProductCategory c : categories) {
            System.out.println(c.getId() + ". " + c.getName());
        }
    }

    public void displayListProductBySupplier(List<Supplier> suppliers) {
        System.out.println("Products by supplier:");
        for(Supplier s : suppliers) {
            System.out.println(s.getId() + ". " + s.getName() + " " + s.getDescription());
        }

    }}
