package com.codecool.shop.controller;

import com.codecool.shop.view.UserInput;

import java.util.Scanner;

/**
 * Created by krzysiek on 5/4/17.
 */
public class MainMenuController {
    public static void MainMenuAction() {
        ProductController productController = new ProductController();

        System.out.println("Select option:");
        System.out.println("1. List all products");
        System.out.println("2. List product by category");
        Scanner scanner = new Scanner(System.in);
        Integer option = UserInput.getUserInput();

        switch (option) {
            case 1:
                productController.listProducts();
                break;
            case 2:
                productController.listProductsByCategory();
                break;
            default:
                System.out.println("Option not found");
        }
    }
}
