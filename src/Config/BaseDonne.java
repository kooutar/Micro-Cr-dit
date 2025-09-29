package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDonne {
    private static BaseDonne instance;  // instance unique
    private Connection connection;

    // Paramètres de connexion
    private final String url = "jdbc:mysql://localhost:3306/microCredit";
    private final String username = "root";
    private final String password = "root";

    // Constructeur privé (empêche new BaseDonne())
    private BaseDonne() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Charger driver
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Connexion réussie !");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL introuvable !", e);
        }
    }

    // Accès à l'instance unique
    public static BaseDonne getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new BaseDonne();
        }
        return instance;
    }

    // Récupérer la connexion
    public Connection getConnection() {
        return connection;
    }
}

