package fr.esiee.modele;

public class Arret {
    private int id;
    private String nom;
    private TypeArret type;

    public Arret() {
        this.type = TypeArret.INTERMEDIAIRE; // Valeur par défaut
    }

    public Arret(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.type = TypeArret.INTERMEDIAIRE; // Valeur par défaut
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public TypeArret getType() { return type; }
    public void setType(TypeArret type) { this.type = type; }
}