package com.codecool.shop.dao;


public class ErrorDb {
    public static void handleExceptionConnectionToDb(Exception exception) {
        System.out.println(exception.getMessage());
        exception.printStackTrace();
        System.out.println("close program");
        System.exit(0);
    }
}
