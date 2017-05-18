import com.codecool.shop.Application;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        File databse = new File("/home/jakub/Java/codecool-shops-forkineye2/src/main/resources/database.db");
        if(args.length == 0 && !databse.exists()){
            Application.stopApplicationBoot();
        }
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
