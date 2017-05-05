package com.codecool.shop.view;

import java.util.Scanner;

public class UserInput {

    public static Integer getUserInput() {

        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input.");
            scanner.next();
        }
        return scanner.nextInt();

    }
}
