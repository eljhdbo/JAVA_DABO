package fr.esiee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {
    public static void main(String[] args) {
        // Chargement du driver MariaDB
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver MariaDB chargé avec succès !");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MariaDB non trouvé !");
            e.printStackTrace();
            System.exit(1);
        }

        // Configuration pour la base locale (PhpMyAdmin)
        String urlLocal = "jdbc:mariadb://localhost:3306/easytrain";
        String userLocal = "root";
        String pwdLocal = "";

        // Configuration pour la base distante (Alwaysdata)
        String urlDistant = "jdbc:mariadb://mysql-alwaysdata.net:3306/eljhdbo_easytrain";
        String userDistant = "eljhdbo";
        String pwdDistant = "mdpbddjdbc123495";

        Connection connectionLocal = null;

        try {
            // Connexion à la bdd locale
            System.out.println("Tentative de connexion à la base locale...");
            connectionLocal = DriverManager.getConnection(urlLocal, userLocal, pwdLocal);
            System.out.println("Connexion réussie à la base locale !");

            // Création d'un Statement pour exécuter des requêtes SQL
            Statement statement = connectionLocal.createStatement();

            // Requête SQL d'insertion dans la table utilisateur
            String sqlInsert = "INSERT INTO utilisateur (login, mdp, nom, prenom, dateEmbauche, role) " +
                    "VALUES ('barryh', 'flashpoint1946', 'Bartholomew Henry', 'Allen', '2023-06-14', 'employee')";

            // Exécution de la requête
            int rowsAffected = statement.executeUpdate(sqlInsert);

            // Récupération d'un utilisateur avec l'ID
            int searchId = 1;
            String sqlSelectById = "SELECT * FROM utilisateur WHERE id = " + searchId;

            System.out.println("\nRecherche de l'utilisateur avec l'ID : " + searchId);
            ResultSet resultSetById = statement.executeQuery(sqlSelectById);

            // La vérification et l'affichage du résultat
            if (resultSetById.next()) {
                System.out.println("Utilisateur trouvé :");
                System.out.println("ID : " + resultSetById.getInt("id"));
                System.out.println("Login : " + resultSetById.getString("login"));
                System.out.println("Nom : " + resultSetById.getString("nom"));
                System.out.println("Prénom : " + resultSetById.getString("prenom"));
                System.out.println("Date d'embauche : " + resultSetById.getDate("dateEmbauche"));
                System.out.println("Rôle : " + resultSetById.getString("role"));
            } else {
                System.out.println("Aucun utilisateur trouvé avec l'ID : " + searchId);
            }

            // Récupérer tous les utilisateurs
            String sqlSelectAll = "SELECT * FROM utilisateur";

            System.out.println("\nListe de tous les utilisateurs :");
            ResultSet resultSetAll = statement.executeQuery(sqlSelectAll);

            // Afficher tous les résultats
            while (resultSetAll.next()) {
                System.out.println("-------------------------");
                System.out.println("ID : " + resultSetAll.getInt("id"));
                System.out.println("Login : " + resultSetAll.getString("login"));
                System.out.println("Nom : " + resultSetAll.getString("nom"));
                System.out.println("Prénom : " + resultSetAll.getString("prenom"));
                System.out.println("Date d'embauche : " + resultSetAll.getDate("dateEmbauche"));
                System.out.println("Rôle : " + resultSetAll.getString("role"));
            }

        } catch (SQLException e) {
            // La gestion des erreurs SQL
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        } finally {
            // Fermeture de la connexion en locale
            if (connectionLocal != null) {
                try {
                    connectionLocal.close();
                    System.out.println("Connexion locale fermée.");
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture de la connexion locale : " + e.getMessage());
                }
            }
        }

        System.out.println("\nFin.");
    }
}
