import com.codecool.shop.Application;

import static spark.Spark.*;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Bye bye :( ");
            }
        });
        new Application(args);
    }
}
