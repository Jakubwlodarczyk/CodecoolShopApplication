import com.codecool.shop.Application;
import com.codecool.shop.controller.MainMenuController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.SQLException;
import java.util.Scanner;

import static spark.Spark.*;


public class Main {

    public static void main(String[] args) {

        new Application();

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/hello", (req, res) -> "Hello World");
    }
}
