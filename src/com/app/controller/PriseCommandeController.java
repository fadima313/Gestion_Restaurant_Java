package com.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;

import com.app.dao.impl.ClientDaoImpl;
import com.app.dao.impl.CommandeDaoImpl;
import com.app.dao.impl.PaiementDaoImpl;
import com.app.models.Client;
import com.app.models.Commande;
import com.app.models.Paiement;
import com.app.exception.DAOException;

public class PriseCommandeController {

    @FXML
    private TextField Nom_Client;

    @FXML
    private TextField NumCommande_Client;

    @FXML
    private TextField Prenom_Client;

    @FXML
    private Button btnAnnuler;
    
    @FXML
    private Button btnVoirRecette;

    @FXML
    private Button btnValider;

    private ClientDaoImpl clientDao; 

    private Commande nouvelleCommande;

    @FXML
    private void initialize() {
        clientDao = new ClientDaoImpl();
        
        
    }

    @FXML
    private void handleValiderButton() {
        String nom = Nom_Client.getText();
        String prenom = Prenom_Client.getText();
        String numCommande = NumCommande_Client.getText();


                    if (!nom.isEmpty() && !prenom.isEmpty() && !numCommande.isEmpty()) {
                        try {
                            Client client = new Client(nom, prenom, numCommande);
                            clientDao.create(client);

                            clearInputFields();
                            showAlert("Succès", "Le client a été ajouté avec succès.", Alert.AlertType.INFORMATION);

                           
                            Commande commande = new Commande(new ArrayList<>(), new Date(), 0.0);
                            commande.setClient(client);
                            //PaiementDaoImpl paiementDao;
							//Paiement paiement = paiementDao.read(PAYMENT_ID);
                            //commande.setPaiement(paiement);

                            MainController.getInstance().showMenuWindow((Stage) btnValider.getScene().getWindow(), commande);
                        } catch (DAOException e) {
                            showAlert("Erreur", "Une erreur est survenue lors de l'ajout du client.", Alert.AlertType.ERROR);
                        }
                    } else {
                        showAlert("Champs vides", "Veuillez remplir tous les champs.", Alert.AlertType.ERROR);
                    }
                }

    @FXML
    private void handleVoirRecetteButton() {
        MainController.getInstance().openSuiviRecetteWindow();
    }


    private void clearInputFields() {
        Nom_Client.clear();
        Prenom_Client.clear();
        NumCommande_Client.clear();
    }
    
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
