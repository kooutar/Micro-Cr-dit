import Config.BaseDonne;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            Connection cnx = BaseDonne.getInstance().getConnection();
            // Faire des requêtes ici...
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}