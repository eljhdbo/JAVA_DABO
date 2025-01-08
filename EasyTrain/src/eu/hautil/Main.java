package eu.hautil;

import eu.hautil.modele.Arret;
import eu.hautil.modele.Trajet;
import eu.hautil.modele.Utilisateur;
import eu.hautil.modele.Role;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Arret arret1 = new Arret(1, "Gotham City");
        Arret arret2 = new Arret(2, "Metropolis");
        Arret arret3 = new Arret(3, "Walla Walla");
        Arret arret4 = new Arret(4, "Los Angeles");

        Trajet trajet1 = new Trajet("TRAIN 1", LocalDateTime.of(2016, 10, 10, 1, 30), LocalDateTime.of(2016, 10, 10, 18, 45), arret1, arret2);
        Trajet trajet2 = new Trajet("TRAIN 2", LocalDateTime.of(1939, 03, 30, 9, 50), LocalDateTime.of(1939, 03, 30, 11, 30), arret3, arret4);

        System.out.println("Trajet 1:");
        System.out.println("Code: " + trajet1.getCode());
        System.out.println("Départ: " + trajet1.getArretDepart().getNom());
        System.out.println("Arrivée: " + trajet1.getArretArrivee().getNom());
        System.out.println("Heure de départ: " + trajet1.getTempsDepart());
        System.out.println("Heure d'arrivée: " + trajet1.getTempsArrivee());
        System.out.println("-----");

        System.out.println("Trajet 2:");
        System.out.println("Code: " + trajet2.getCode());
        System.out.println("Départ: " + trajet2.getArretDepart().getNom());
        System.out.println("Arrivée: " + trajet2.getArretArrivee().getNom());
        System.out.println("Heure de départ: " + trajet2.getTempsDepart());
        System.out.println("Heure d'arrivée: " + trajet2.getTempsArrivee());
        System.out.println("-----");

        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setNom("West");
        utilisateur1.setPrenom("Adam");
        utilisateur1.setRole(Role.admin);

        Utilisateur utilisateur2 = new Utilisateur(2, "chevaliernoir", "jesuisbatman1966", "Wayne", "Bruce", LocalDate.of(1939, 3, 30), Role.employee);

        System.out.println("Utilisateur 1:");
        utilisateur1.afficherInfos();

        System.out.println("Utilisateur 2:");
        utilisateur2.afficherInfos();
    }
}
