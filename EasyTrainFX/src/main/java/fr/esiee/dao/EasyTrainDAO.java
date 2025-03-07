package fr.esiee.dao;

import fr.esiee.modele.Arret;
import fr.esiee.modele.Role;
import fr.esiee.modele.Trajet;
import fr.esiee.modele.Utilisateur;
import fr.esiee.modele.;

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
                utilisateurs.add(new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("mdp"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateEmbauche").toLocalDate(),
                        Role.valueOf(rs.getString("role").toLowerCase())
                ));
            }
        } catch (SQLException e) {
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
            e.printStackTrace();
        }
        return false;
    }

    public Arret getArretById(int id) {
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM arret WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Arret arret = new Arret(rs.getInt("id"), rs.getString("nom"));
                arret.setType(.valueOf(rs.getString("type_arret")));
                return arret;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean nomArretExiste(String nom) {
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM arret WHERE nom = ?")) {
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Arret> getAllArrets() {
        List<Arret> arrets = new ArrayList<>();
        try (Connection conn = getConnexion();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM arret");
            while (rs.next()) {
                Arret arret = new Arret(rs.getInt("id"), rs.getString("nom"));
                arret.setType(valueOf(rs.getString("type_arret")));
                arrets.add(arret);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrets;
    }

    public boolean ajouterArret(Arret a) {
        String sql = "INSERT INTO arret (nom, type_arret) VALUES (?, ?)";
        try (Connection conn = getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, a.getNom());
            stmt.setString(2, a.getType().toString());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    a.setId(generatedKeys.getInt(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Méthodes pour les trajets restent inchangées
}