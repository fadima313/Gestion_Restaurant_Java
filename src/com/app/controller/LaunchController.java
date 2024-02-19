package com.app.controller;

import java.io.IOException;

import com.app.exception.DAOException;
import com.app.metier.UserMetier;
import com.app.models.Administrateur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LaunchController {
    
    private MainController mainController;

    private Stage stage;
    
    @FXML
    private Button lancement;

    private UserMetier userMetier;
    
    @FXML
    private void initialize() {
    	
       
        
    }

   
    @FXML
    private void onLancementClick(ActionEvent event) throws IOException {
        this.stage= new Stage();
        Stage stage = (Stage) lancement.getScene().getWindow();
        stage.close();
        MainController.getInstance().showAuthenticationUI(stage);
        //MainController.getInstance().showGestionProduitsWindow(stage);
    }
    
    
}
