package com.codecool.shop.controller;


import com.codecool.shop.view.UserInput;


public class MainMenuController {

    static ProductController productController = new ProductController();
    static BasketController basketController = new BasketController();


        public static void mainMenuAction() {
            while(true){
                System.out.println("Select option:");
                System.out.println("1. List all products.");
                System.out.println("2. List products by category.");
                System.out.println("3. List products by supplier.");
                System.out.println("4. Add to cart.");
                System.out.println("5. Display basket.");

                Integer option = UserInput.getUserInput();

                switch (option) {
                    case 1:
                        productController.listProducts();
                        break;
                    case 2:
                        productController.listProductByCategory();
                        break;
                    case 3:
                        productController.listProductsBySupplier();
                        break;
                    case 4:
                        basketController.addToCartAction();
                        break;
                    case 5:
                        basketController.displayCartAction();
                        break;
                    default:
                        System.out.println("Option not found.");
            }
        }
    }
}
