package com.app.controller;

import com.app.models.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.SelectionMode;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.db.DBManager;
import com.app.enums.Role;
import com.app.metier.UserMetier;

public class UserEditUIController {

	@FXML
	private TextField nomField;
	@FXML
	private TextField prenomField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField telephoneField;
	@FXML
	private TextField loginField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private ComboBox<Role> roleComboBox;

	private Stage dialogStage;
	private Utilisateur user;
	private boolean validerClicked;

	private UserMetier userMetier;

	@FXML
	private void initialize() {
		userMetier = new UserMetier();
		roleComboBox.getItems().clear();
		ObservableList<Role> roles = FXCollections.observableArrayList(Role.Admin, Role.Chef, Role.Restaurateur);

		roleComboBox.setItems(roles);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setUser(Utilisateur user) {
		this.user = user;

		nomField.setText(user.getFirstname());
		prenomField.setText(user.getLastname());
		emailField.setText(user.getEmail());
		telephoneField.setText(user.getTelephone());
		loginField.setText(user.getLogin());
		passwordField.setText(user.getPassword());
		//roleComboBox.getSelectionModel().select(user.getRole().getName());
	}

	/**
	 * Called when the user clicks Valider.
	 */
	@FXML
	private void handleValider() {
	    if (isInputValid()) {
	        System.out.println(nomField.getText()); 
	        
	        if (user == null) {
	            user = new Utilisateur();
	        }

	        user.setFirstname(nomField.getText());
	        user.setLastname(prenomField.getText());
	        user.setEmail(emailField.getText());
	        user.setTelephone(telephoneField.getText());
	        user.setLogin(loginField.getText());
	        user.setPassword(passwordField.getText());
	        user.setRole(roleComboBox.getSelectionModel().getSelectedItem());
	        
	        // Création ou mise à jour de l'utilisateur en appelant le métier
	        try {
	            if (user.getId() == 0) {
	                userMetier.creerUtilisation(user); 
	            } else {
	                userMetier.modifierUtilisateur(user); 
	            }
	            validerClicked = true;
	            dialogStage.close(); 
	        } catch (Exception e) {

	        }

	    } else {
	        System.out.println("Validation failed!");
	    }
	}

	 private boolean isInputValid() { // Validates the user input in the text fields.
		 String errorMessage = "";

		 if (nomField.getText() == null || nomField.getText().length() == 0) 
			 errorMessage += "Le nom n'est pas renseigné !\n"; 

		 if (errorMessage.length() == 0) return true;
		 else { // Show the error message.            
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.initOwner(dialogStage);
			 alert.setTitle("Champs non renseignés et/ou invalides !");
			 alert.setHeaderText("Veuillez remplir tous les champs svp !");
			 alert.setContentText(errorMessage);

			 alert.showAndWait();
			 return false;
		 }
	 }

	 @FXML // Called when the user clicks Annuler.
	 private void handleAnnuler() {
		 validerClicked = false;
		 dialogStage.close();
	 }

	 public boolean isValiderClicked() {		
		 return validerClicked;
	 }
}