package eu.hautil.modele;

import java.time.LocalDate;

public class Utilisateur {

    private int id;
    private String login;
    private String mdp;
    private String nom;
    private String prenom;
    private LocalDate dateEmbauche;
    private Role role;

    public Utilisateur() {
        this.id = 1;
        this.login = "capedcrusader";
        this.mdp = "anderson1966";
        this.nom = "West";
        this.prenom = "Adam";
        this.dateEmbauche = LocalDate.now();
        this.role = Role.EMPLOYE;
    }

    public Utilisateur(int id, String login, String mdp, String nom, String prenom, LocalDate dateEmbauche, Role role) {
        this.id = id;
        this.login = login;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.dateEmbauche = dateEmbauche;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void afficherInfos() {
        System.out.println("ID: " + id);
        System.out.println("Login: " + login);
        System.out.println("Nom: " + nom + " " + prenom);
        System.out.println("Date d'embauche: " + dateEmbauche);
        System.out.println("Rôle: " + role);
        System.out.println("-----");
    }
}
