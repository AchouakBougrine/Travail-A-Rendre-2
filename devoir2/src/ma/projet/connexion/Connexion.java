/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.connexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.util.Properties;
public class Connexion {
    private static Connection connection;

    static {
        try {
            FileInputStream f = new FileInputStream("base.properties");
            Properties p = new Properties();
            p.load(f);
            String url = p.getProperty("jdbc.url");
            String login = p.getProperty("jdbc.username");
            String password = p.getProperty("jdbc.password");
            String driver = p.getProperty("jdbc.driver");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion réussie à la base de données.");
        } catch (Exception ex) {
            System.out.println("Erreur de connexion: " + ex.getMessage());
        }
    }
    public static Connection getConnection() {
        if (connection == null) {
            System.out.println("La connexion est null.");
        }
        return connection;
    }
}
