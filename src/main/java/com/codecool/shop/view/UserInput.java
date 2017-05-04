package com.codecool.shop.view;

import java.util.Scanner;

/**
 * Created by krzysiek on 5/4/17.
 */
public class UserInput {
    public static Integer getUserInput() {
        Scanner scanner = new Scanner(System.in);
        while(!scanner.hasNextInt()) {
            System.out.println("Invalid input. Try again");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
