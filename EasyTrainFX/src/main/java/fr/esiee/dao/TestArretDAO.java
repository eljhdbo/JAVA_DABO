package fr.esiee.dao;

import fr.esiee.modele.Arret;
import fr.esiee.modele.TypeArret;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestArretDAO {
    private EasyTrainDAO dao = new EasyTrainDAO();

    @Test
    public void testAjoutArret() {
        Arret arret = new Arret(0, "Gare Test");
        arret.setType(TypeArret.TERMINUS);

        assertTrue(dao.ajouterArret(arret));
        assertTrue(arret.getId() > 0);

        Arret arretRecupere = dao.getArretById(arret.getId());
        assertNotNull(arretRecupere);
        assertEquals(arret.getNom(), arretRecupere.getNom());
        assertEquals(arret.getType(), arretRecupere.getType());
    }

    @Test
    public void testNomArretExiste() {
        String nomTest = "Gare Test " + System.currentTimeMillis();
        assertFalse(dao.nomArretExiste(nomTest));

        Arret arret = new Arret(0, nomTest);
        arret.setType(TypeArret.INTERMEDIAIRE);
        dao.ajouterArret(arret);

        assertTrue(dao.nomArretExiste(nomTest));
    }
}