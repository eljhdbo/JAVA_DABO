package eu.hautil.dao;

import eu.hautil.modele.Arret;
import eu.hautil.modele.Role;
import eu.hautil.modele.Trajet;
import eu.hautil.modele.Utilisateur;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EasyTrainDAO {
    private static final String URL = "jdbc:mariadb://localhost:3306/easytrain";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            // Chargement explicite du driver
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver MariaDB chargé avec succès!");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur de chargement du driver MariaDB!");
            e.printStackTrace();
        }
    }

    private Connection getConnexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Méthodes pour la gestion des utilisateurs
    public Utilisateur getUtilisateurById(int id) {
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM utilisateur WHERE id = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("mdp"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateEmbauche").toLocalDate(),
                        Role.valueOf(rs.getString("role"))
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try (Connection conn = getConnexion();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateur");
            while (rs.next()) {
                String roleStr = rs.getString("role").toLowerCase();
                Role role = Role.valueOf(roleStr);
                utilisateurs.add(new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("mdp"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateEmbauche").toLocalDate(),
                        role
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
            e.printStackTrace();
        }
        return utilisateurs;
    }

    public boolean ajouterUtilisateur(Utilisateur u) {
        String sql = "INSERT INTO utilisateur (login, mdp, nom, prenom, dateEmbauche, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, u.getLogin());
            stmt.setString(2, u.getMdp());
            stmt.setString(3, u.getNom());
            stmt.setString(4, u.getPrenom());
            stmt.setDate(5, Date.valueOf(u.getDateEmbauche()));
            stmt.setString(6, u.getRole().toString());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    u.setId(generatedKeys.getInt(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Méthodes pour la gestion des arrêts
    public Arret getArretById(int id) {
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM arret WHERE id = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Arret(rs.getInt("id"), rs.getString("nom"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'arrêt : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Arret> getAllArrets() {
        List<Arret> arrets = new ArrayList<>();
        try (Connection conn = getConnexion();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM arret");
            while (rs.next()) {
                arrets.add(new Arret(rs.getInt("id"), rs.getString("nom")));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des arrêts : " + e.getMessage());
            e.printStackTrace();
        }
        return arrets;
    }

    public boolean ajouterArret(Arret a) {
        String sql = "INSERT INTO arret (nom) VALUES (?)";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, a.getNom());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    a.setId(generatedKeys.getInt(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'arrêt : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Méthodes pour la gestion des trajets
    public Trajet getTrajetById(String code) {
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT t.*, ad.id as depart_id, ad.nom as depart_nom, " +
                             "aa.id as arrivee_id, aa.nom as arrivee_nom " +
                             "FROM trajet t " +
                             "JOIN arret ad ON t.arretDepart_id = ad.id " +
                             "JOIN arret aa ON t.arretArrivee_id = aa.id " +
                             "WHERE t.code = ?")) {

            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Arret depart = new Arret(rs.getInt("depart_id"), rs.getString("depart_nom"));
                Arret arrivee = new Arret(rs.getInt("arrivee_id"), rs.getString("arrivee_nom"));

                return new Trajet(
                        rs.getString("code"),
                        rs.getTimestamp("tempsDepart").toLocalDateTime(),
                        rs.getTimestamp("tempsArrivee").toLocalDateTime(),
                        depart,
                        arrivee
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du trajet : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Trajet> getAllTrajets() {
        List<Trajet> trajets = new ArrayList<>();
        try (Connection conn = getConnexion();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(
                    "SELECT t.*, ad.id as depart_id, ad.nom as depart_nom, " +
                            "aa.id as arrivee_id, aa.nom as arrivee_nom " +
                            "FROM trajet t " +
                            "JOIN arret ad ON t.arretDepart_id = ad.id " +
                            "JOIN arret aa ON t.arretArrivee_id = aa.id");

            while (rs.next()) {
                Arret depart = new Arret(rs.getInt("depart_id"), rs.getString("depart_nom"));
                Arret arrivee = new Arret(rs.getInt("arrivee_id"), rs.getString("arrivee_nom"));

                trajets.add(new Trajet(
                        rs.getString("code"),
                        rs.getTimestamp("tempsDepart").toLocalDateTime(),
                        rs.getTimestamp("tempsArrivee").toLocalDateTime(),
                        depart,
                        arrivee
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des trajets : " + e.getMessage());
            e.printStackTrace();
        }
        return trajets;
    }

    public boolean ajouterTrajet(Trajet t) {
        String sql = "INSERT INTO trajet (code, tempsDepart, tempsArrivee, arretDepart_id, arretArrivee_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCode());
            stmt.setTimestamp(2, Timestamp.valueOf(t.getTempsDepart()));
            stmt.setTimestamp(3, Timestamp.valueOf(t.getTempsArrivee()));
            stmt.setInt(4, t.getArretDepart().getId());
            stmt.setInt(5, t.getArretArrivee().getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du trajet : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
