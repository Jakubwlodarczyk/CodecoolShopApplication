import com.codecool.shop.controller.MainMenuController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--create-tables")) {
            try {
                SqliteJDBCConnector.createTables();
            } catch (SQLException e) {
                System.out.println("Cannot create tables in db.");
                e.printStackTrace();
            }
        }
        MainMenuController menu = new MainMenuController();
        menu.mainMenuAction();



    }
}
