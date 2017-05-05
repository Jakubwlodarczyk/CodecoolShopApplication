package com.codecool.shop.controller;

import com.codecool.shop.view.UserInput;

import java.util.Scanner;

/**
 * Created by krzysiek on 5/4/17.
 */
public class MainMenuController {
    public static void MainMenuAction() {
        ProductController productController = new ProductController();
        BasketController basketController = new BasketController();

        System.out.println("Select option:");
        System.out.println("1. List products by supplier");
        System.out.println("2. List products by category");
        System.out.println("4. Basket controller");
        Scanner scanner = new Scanner(System.in);
        Integer option = UserInput.getUserInput();

        switch (option) {
            case 1:
                productController.listProductsBySupplier();
                break;
            case 2:
                productController.listProductsByCategory();
                break;
            case 4:
                basketController.addToCardAction();
                break;
            default:
                System.out.println("Option not found");
        }
    }
}
