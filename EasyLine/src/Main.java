public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Voyage naija = new Voyage();

        naija.setDestination("Naija");
        naija.setDuree(3);
        naija.setPrix(100);

        // Afficher les informations du voyage
        System.out.println("Destination : " + naija.getDestination());
        System.out.println("Durée : " + naija.getDuree() + " jours");
        System.out.println("Prix : " + naija.getPrix() + " euros");
    }
}
