package com.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.app.metier.UserMetier;
import com.app.models.Utilisateur;

public class AuthUIController {

    private Stage stage;

    @FXML
    private Button btnConnexion;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private Button btnRetour;

    @FXML
    void handleClickedConnexion(ActionEvent event) {
        String login = txtLogin.getText();
        String password = txtPassword.getText();
        System.out.println("Authentification esss");
        Utilisateur user = authenticateUser(login, password);
        if (user != null) {
        	
            System.out.println("Authentification réussie !");
            System.out.println(user.toString());
            openNextWindow(user); // Redirige vers la fenêtre appropriée en fonction du rôle
        } else {
            System.out.println("Authentification échouée !");
        
        }
    }

    
    @FXML
    void handleClickedRetour(ActionEvent event) {
        // Affiche la fenêtre de lancement
        MainController.getInstance().showLaunchUI(stage);
    } 
    
    private Utilisateur authenticateUser(String login, String password) {
        // Implémente la logique d'authentification en utilisant votre métier
        UserMetier userMetier = new UserMetier();
        return userMetier.authenticate(login, password);
    }


    private void openNextWindow(Utilisateur user) {
        // Ici, en fonction du rôle de l'utilisateur (login), ouvrez la fenêtre appropriée
    	
        if (user.getRole().getName().equals("Admin")) {
            MainController.getInstance().showGestionUtilisateursWindow(stage);
        } else if (user.getRole().getName().equals("Restaurateur")) {
        
        	MainController.getInstance().showPriseCommandeWindow(stage);
        } else if (user.getRole().getName().equals("Chef")) {
        	
        	MainController.getInstance().showGestionProduitsWindow(stage);
        }
        // Autres cas possibles pour les autres rôles...
    }
}
