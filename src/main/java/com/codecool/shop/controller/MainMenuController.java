package com.codecool.shop.controller;


import com.codecool.shop.view.UserInput;

import java.util.Scanner;

public class MainMenuController {

    static ProductController productController = new ProductController();

    public static void mainMenuAction() {
        System.out.println("Select option:");
        System.out.println("1. List all products.");
        System.out.println("2. List products by category.");

        Integer option = UserInput.getUserInput();

        switch (option) {
            case 1:
                productController.listProducts();
                break;
            case 2:
                productController.listProductByCategory();
                break;
            case 3:
                break;
            default:
                System.out.println("Option not found.");
        }
    }
}
