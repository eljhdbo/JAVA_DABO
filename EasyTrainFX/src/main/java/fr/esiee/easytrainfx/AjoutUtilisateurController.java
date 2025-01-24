package fr.esiee.easytrainfx;

import fr.esiee.dao.EasyTrainDAO;
import fr.esiee.modele.Role;
import fr.esiee.modele.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class AjoutUtilisateurController {
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private DatePicker dateEmbauchePicker;
    @FXML private ComboBox<Role> roleComboBox;
    @FXML private Label messageLabel;

    private EasyTrainDAO dao;

    @FXML
    public void initialize() {
        dao = new EasyTrainDAO();
        roleComboBox.getItems().addAll(Role.values());
        messageLabel.setWrapText(true);
    }

    @FXML
    protected void onResetButtonClick() {
        loginField.clear();
        passwordField.clear();
        nomField.clear();
        prenomField.clear();
        dateEmbauchePicker.setValue(null);
        roleComboBox.setValue(null);
        messageLabel.setText("");
        messageLabel.setTextFill(Color.BLACK);
    }

    @FXML
    protected void onAjouterButtonClick() {
        if (validateFields()) {
            try {
                Utilisateur utilisateur = new Utilisateur(
                        0,
                        loginField.getText(),
                        passwordField.getText(),
                        nomField.getText(),
                        prenomField.getText(),
                        dateEmbauchePicker.getValue(),
                        roleComboBox.getValue()
                );

                if (dao.ajouterUtilisateur(utilisateur)) {
                    messageLabel.setTextFill(Color.GREEN);
                    messageLabel.setText(String.format(
                            "Utilisateur ajouté avec succès !\nID: %d\nNom: %s %s\nLogin: %s\nRôle: %s\nDate d'embauche: %s",
                            utilisateur.getId(),
                            utilisateur.getNom(),
                            utilisateur.getPrenom(),
                            utilisateur.getLogin(),
                            utilisateur.getRole(),
                            utilisateur.getDateEmbauche()
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
        if (loginField.getText().isEmpty()) {
            afficherErreur("Le login est requis");
            return false;
        }
        if (passwordField.getText().isEmpty()) {
            afficherErreur("Le mot de passe est requis");
            return false;
        }
        if (nomField.getText().isEmpty()) {
            afficherErreur("Le nom est requis");
            return false;
        }
        if (prenomField.getText().isEmpty()) {
            afficherErreur("Le prénom est requis");
            return false;
        }
        if (dateEmbauchePicker.getValue() == null) {
            afficherErreur("La date d'embauche est requise");
            return false;
        }
        if (roleComboBox.getValue() == null) {
            afficherErreur("Le rôle est requis");
            return false;
        }
        return true;
    }

    private void afficherErreur(String message) {
        messageLabel.setTextFill(Color.RED);
        messageLabel.setText("Erreur: " + message);
    }
}