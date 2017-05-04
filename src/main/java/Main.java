import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {

    static ProductController productController = new ProductController();

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--create-tables")) {
            try {
                SqliteJDBCConnector.createTables();
            } catch (SQLException e) {
                System.out.println("Cannot create tables in db.");
            }
        }
        System.out.println("Select option:");
        System.out.println("1. List all products.");
        System.out.println("2. List products by category.");
        Scanner reader = new Scanner(System.in);
        while(!reader.hasNextInt()) {
            System.out.println("Invalid input.");
            reader.next();
        }
        Integer option = reader.nextInt();
        switch(option) {
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
