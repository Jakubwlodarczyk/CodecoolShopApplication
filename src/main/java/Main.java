import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCCOnnector;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--create-tables")) {
            try {
                SqliteJDBCCOnnector.createTables();
            } catch (SQLException e) {
                System.out.println("Cannot create tables in db.");
            }
        }
        ProductController productController = new ProductController();
        productController.listProducts();
    }
}
