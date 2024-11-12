package fr.esiee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
    public static void main(String[] args) {
        // Configuration pour la base locale (PhpMyAdmin)
        String urlLocal = "jdbc:mariadb://localhost:3306/EasyTrain";
        String userLocal = "root";
        String pwdLocal = "";

        // Configuration pour la base distante (Alwaysdata)
        String urlDistant = "jdbc:mariadb://localhost:3306/EasyTrain";
        String userDistant = "root";
        String pwdDistant = "";

        Connection connectionLocal = null;
        Connection connectionDistant = null;

        // Connexion à la base locale
        try {
            System.out.println("Tentative de connexion à la base locale...");
            connectionLocal = DriverManager.getConnection(urlLocal, userLocal, pwdLocal);
            System.out.println("Connexion réussie à la base locale ");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base locale : " + e.getMessage());
        } finally {
            // Fermeture de la connexion locale
            if (connectionLocal != null) {
                try {
                    connectionLocal.close();
                    System.out.println("Connexion locale fermée.");
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture de la connexion locale : " + e.getMessage());
                }
            }
        }

        // Connexion à la base distante
        try {
            System.out.println("Tentative de connexion à la base distante (Alwaysdata)...");
            connectionDistant = DriverManager.getConnection(urlDistant, userDistant, pwdDistant);
            System.out.println("Connexion réussie à la base distante !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base distante : " + e.getMessage());
        } finally {
            // Fermeture de la connexion distante
            if (connectionDistant != null) {
                try {
                    connectionDistant.close();
                    System.out.println("Connexion à la base distante fermée.");
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture de la connexion distante : " + e.getMessage());
                }
            }
        }

        System.out.println("Fin.");
    }
}
