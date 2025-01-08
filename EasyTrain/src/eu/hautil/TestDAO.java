package eu.hautil;

import eu.hautil.dao.EasyTrainDAO;
import eu.hautil.modele.Arret;
import eu.hautil.modele.Role;
import eu.hautil.modele.Trajet;
import eu.hautil.modele.Utilisateur;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestDAO {
    public static void main(String[] args) {
        // Création de l'instance DAO
        EasyTrainDAO dao = new EasyTrainDAO();

        // Test des méthodes Utilisateur
        System.out.println("============ Test des méthodes Utilisateur ============");
        testMethodesUtilisateur(dao);

        // Test des méthodes Arret
        System.out.println("\n============ Test des méthodes Arret ============");
        testMethodesArret(dao);

        // Test des méthodes Trajet
        System.out.println("\n============ Test des méthodes Trajet ============");
        testMethodesTrajet(dao);
    }

    private static void testMethodesUtilisateur(EasyTrainDAO dao) {
        // Test ajouterUtilisateur
        System.out.println("Test d'ajout d'un utilisateur :");
        Utilisateur newUser = new Utilisateur(
                0,
                "superman",
                "cryptonite1938",
                "Kent",
                "Clark",
                LocalDate.now(),
                Role.employee
        );

        if (dao.ajouterUtilisateur(newUser)) {
            System.out.println("✓ Ajout réussi avec l'ID : " + newUser.getId());
        } else {
            System.out.println("✗ Échec de l'ajout");
        }

        // Test getUtilisateurById
        System.out.println("\nTest de récupération par ID :");
        Utilisateur user = dao.getUtilisateurById(newUser.getId());
        if (user != null) {
            System.out.println("✓ Utilisateur trouvé :");
            afficherUtilisateur(user);
        } else {
            System.out.println("✗ Utilisateur non trouvé");
        }

        // Test getAllUtilisateurs
        System.out.println("\nTest de récupération de tous les utilisateurs :");
        List<Utilisateur> users = dao.getAllUtilisateurs();
        if (!users.isEmpty()) {
            System.out.println("✓ Liste des utilisateurs récupérée :");
            for (Utilisateur u : users) {
                afficherUtilisateur(u);
            }
        } else {
            System.out.println("✗ Aucun utilisateur trouvé");
        }
    }

    private static void testMethodesArret(EasyTrainDAO dao) {
        // Test ajouterArret
        System.out.println("Test d'ajout d'un arrêt :");
        Arret newArret = new Arret(0, "Central City");

        if (dao.ajouterArret(newArret)) {
            System.out.println("✓ Ajout réussi avec l'ID : " + newArret.getId());
        } else {
            System.out.println("✗ Échec de l'ajout");
        }

        // Test getArretById
        System.out.println("\nTest de récupération par ID :");
        Arret arret = dao.getArretById(newArret.getId());
        if (arret != null) {
            System.out.println("✓ Arrêt trouvé :");
            afficherArret(arret);
        } else {
            System.out.println("✗ Arrêt non trouvé");
        }

        // Test getAllArrets
        System.out.println("\nTest de récupération de tous les arrêts :");
        List<Arret> arrets = dao.getAllArrets();
        if (!arrets.isEmpty()) {
            System.out.println("✓ Liste des arrêts récupérée :");
            for (Arret a : arrets) {
                afficherArret(a);
            }
        } else {
            System.out.println("✗ Aucun arrêt trouvé");
        }
    }

    private static void testMethodesTrajet(EasyTrainDAO dao) {
        // Création des arrêts pour le test
        System.out.println("Création des arrêts pour le test du trajet :");
        Arret depart = new Arret(0, "Star City");
        Arret arrivee = new Arret(0, "Coast City");

        if (dao.ajouterArret(depart) && dao.ajouterArret(arrivee)) {
            System.out.println("✓ Arrêts créés avec succès");
        } else {
            System.out.println("✗ Échec de création des arrêts");
            return;
        }

        // Test ajouterTrajet
        System.out.println("\nTest d'ajout d'un trajet :");
        Trajet newTrajet = new Trajet(
                "FLASH1",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                depart,
                arrivee
        );

        if (dao.ajouterTrajet(newTrajet)) {
            System.out.println("✓ Trajet ajouté avec succès");
        } else {
            System.out.println("✗ Échec de l'ajout du trajet");
        }

        // Test getTrajetById
        System.out.println("\nTest de récupération par Code :");
        Trajet trajet = dao.getTrajetById("FLASH1");
        if (trajet != null) {
            System.out.println("✓ Trajet trouvé :");
            afficherTrajet(trajet);
        } else {
            System.out.println("✗ Trajet non trouvé");
        }

        // Test getAllTrajets
        System.out.println("\nTest de récupération de tous les trajets :");
        List<Trajet> trajets = dao.getAllTrajets();
        if (!trajets.isEmpty()) {
            System.out.println("✓ Liste des trajets récupérée :");
            for (Trajet t : trajets) {
                afficherTrajet(t);
                System.out.println("---");
            }
        } else {
            System.out.println("✗ Aucun trajet trouvé");
        }
    }

    private static void afficherUtilisateur(Utilisateur u) {
        System.out.println("  ID: " + u.getId());
        System.out.println("  Login: " + u.getLogin());
        System.out.println("  Nom: " + u.getNom() + " " + u.getPrenom());
        System.out.println("  Date d'embauche: " + u.getDateEmbauche());
        System.out.println("  Rôle: " + u.getRole());
        System.out.println("  -----");
    }

    private static void afficherArret(Arret a) {
        System.out.println("  ID: " + a.getId());
        System.out.println("  Nom: " + a.getNom());
        System.out.println("  -----");
    }

    private static void afficherTrajet(Trajet t) {
        System.out.println("  Code: " + t.getCode());
        System.out.println("  Départ: " + t.getArretDepart().getNom());
        System.out.println("  Arrivée: " + t.getArretArrivee().getNom());
        System.out.println("  Temps départ: " + t.getTempsDepart());
        System.out.println("  Temps arrivée: " + t.getTempsArrivee());
    }
}