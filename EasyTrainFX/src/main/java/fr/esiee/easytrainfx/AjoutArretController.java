package fr.esiee.easytrainfx;

import fr.esiee.dao.EasyTrainDAO;
import fr.esiee.modele.Arret;
import fr.esiee.modele.;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class AjoutArretController {
    @FXML private TextField nomField;
    @FXML private ComboBox<> typeArretComboBox;
    @FXML private Label messageLabel;

    private EasyTrainDAO dao;

    @FXML
    public void initialize() {
        dao = new EasyTrainDAO();
        typeArretComboBox.getItems().addAll(.values());
        messageLabel.setWrapText(true);
    }

    @FXML
    protected void onResetButtonClick() {
        nomField.clear();
        typeArretComboBox.setValue(null);
        messageLabel.setText("");
        messageLabel.setTextFill(Color.BLACK);
    }

    @FXML
    protected void onAjouterButtonClick() {
        if (validateFields()) {
            String nom = nomField.getText().trim();

            if (dao.nomArretExiste(nom)) {
                afficherErreur("Ce nom d'arrêt existe déjà");
                return;
            }

            try {
                Arret arret = new Arret(0, nom);
                arret.setType(typeArretComboBox.getValue());

                if (dao.ajouterArret(arret)) {
                    messageLabel.setTextFill(Color.GREEN);
                    messageLabel.setText(String.format(
                            "Arrêt ajouté avec succès !\nID: %d\nNom: %s\nType: %s",
                            arret.getId(),
                            arret.getNom(),
                            arret.getType()
                    ));
                } else {
                    afficherErreur("Erreur lors de l'ajout dans la base de données");
                }
            } catch (Exception e) {
                afficherErreur("Erreur système: " + e.getMessage());
            }
        }
    }

    private boolean validateFields() {
        if (nomField.getText().trim().isEmpty()) {
            afficherErreur("Le nom est requis");
            return false;
        }
        if (typeArretComboBox.getValue() == null) {
            afficherErreur("Le type d'arrêt est requis");
            return false;
        }
        return true;
    }

    private void afficherErreur(String message) {
        messageLabel.setTextFill(Color.RED);
        messageLabel.setText("Erreur: " + message);
    }
}