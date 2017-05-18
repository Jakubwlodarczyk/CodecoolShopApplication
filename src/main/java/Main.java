import com.codecool.shop.Application;
import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Application app = Application.getApplication();
        if(args.length>0){
            if (args[0].equals("--init-db")) {
                app.initializeTables();
            } else if (args[0].equals("--migrate-db")) {
                app.migrateTables();
            }
        }
    }
}
