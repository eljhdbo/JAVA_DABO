package fr.esiee.projet_junit5;

public class Devise {
    private int quantite;
    private String monnaie;

    public Devise(int somme, String monnaie) {
        this.quantite = somme;
        this.monnaie = monnaie;
    }
    public int getQuantite() {
        return quantite;
    }
    public String getMonnaie() {
        return monnaie;
    }
    public Devise add(Devise m) {
        return new Devise(getQuantite()+m.getQuantite(), getMonnaie());
    }
}