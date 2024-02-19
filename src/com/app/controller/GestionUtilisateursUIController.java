package com.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.app.dao.impl.UserDaoImpl;
import com.app.exception.DAOException;
import com.app.models.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GestionUtilisateursUIController {
	
	@FXML
	private AnchorPane rootAnchorPane;


    @FXML
    private TableView<Utilisateur> utilisateurTable;
    
    @FXML
    private TableColumn<Utilisateur, Integer> ID;
    
    @FXML
    private TableColumn<Utilisateur, String> Prenom;
    
    @FXML
    private TableColumn<Utilisateur, String> Nom;
    
    @FXML
    private TableColumn<Utilisateur, String> Login;
    
    @FXML
    private Label nomLabel;
    
    @FXML
    private Label prenomLabel;
    
    @FXML
    private Label emailLabel;
    
    @FXML
    private Label telephoneLabel;
    
    @FXML
    private Label loginLabel;
    
    @FXML
    private Label passwordLabel;
    
    @FXML
    private TextField barreRecherche;
    
    @FXML
    private Label roleLabel;
    
    @FXML
    private Button createButton;
    
    @FXML
    private Button editButton;
    
    @FXML
    private Button ModifyButton;
    
    @FXML
    private Button btnLogOut;
    
    private Utilisateur userToEdit;
    
    @FXML
    private Button deleteButton;
    
    private UserDaoImpl userDao = new UserDaoImpl();
    
    

    @FXML
    private void initialize() throws DAOException {
    	
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        Login.setCellValueFactory(new PropertyValueFactory<>("login"));

        utilisateurTable.getItems().addAll(userDao.list());
        
        utilisateurTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            editButton.setDisable(newValue == null);
            deleteButton.setDisable(newValue == null);
        });
        
     
        barreRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
				filtrerUtilisateurs(newValue, userDao.list());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }

    @FXML
    private void handleCreateButton(ActionEvent event) throws DAOException {
 
        
        try {
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserEditUI.fxml"));
            Parent root = loader.load();
            
            UserEditUIController editController = loader.getController();
            Stage createStage = new Stage();
            editController.setDialogStage(createStage);
            
            createStage.setTitle("Créer Utilisateur");
            createStage.setScene(new Scene(root));
            
            
            createStage.initModality(Modality.APPLICATION_MODAL);
            
            createStage.showAndWait();
            
            utilisateurTable.getItems().setAll(userDao.list());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDetailButton(ActionEvent event) throws DAOException {
        Utilisateur selectedUser = utilisateurTable.getSelectionModel().getSelectedItem();
        
        if (selectedUser != null) {
            nomLabel.setText(selectedUser.getFirstname());
            prenomLabel.setText(selectedUser.getLastname());
            emailLabel.setText(selectedUser.getEmail());
            telephoneLabel.setText(selectedUser.getTelephone());
            loginLabel.setText(selectedUser.getLogin());
            passwordLabel.setText(selectedUser.getPassword());
            roleLabel.setText(selectedUser.getRole().getName());
}}
            
            

    @FXML
    private void handleDeleteButton(ActionEvent event) throws DAOException {
        Utilisateur selectedUser = utilisateurTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userDao.delete(selectedUser.getId());
            utilisateurTable.getItems().remove(selectedUser);
        } else {
            showWarningAlert("Aucun utilisateur sélectionné.");
        }
    }
    
    
    
    @FXML
    private void handleModifyButton(ActionEvent event) throws DAOException {
        Utilisateur selectedUser = utilisateurTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserEditUI.fxml"));
                Parent root = loader.load();
                
                UserEditUIController editController = loader.getController();
                editController.setUser(selectedUser); // Pass the selected user
                
                Stage modifierStage = new Stage();
                modifierStage.setTitle("Modifier Utilisateur");
                modifierStage.setScene(new Scene(root));
                
                modifierStage.initModality(Modality.APPLICATION_MODAL);
                
                modifierStage.showAndWait();
               
                
                utilisateurTable.getItems().setAll(userDao.list());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showWarningAlert("Aucun utilisateur sélectionné.");
        }
    }

    
    private void filtrerUtilisateurs(String recherche, List<Utilisateur> allUsers) {
        // Clear the table first
        utilisateurTable.getItems().clear();
        
        // Filter and add matching users to the table
        List<Utilisateur> filteredUsers = allUsers.stream()
                .filter(user -> userMatchesSearch(user, recherche))
                .collect(Collectors.toList());
        
        utilisateurTable.getItems().addAll(filteredUsers);
    }

    private boolean userMatchesSearch(Utilisateur user, String recherche) {
        // Implement your search logic here, e.g., check if user's name or other fields match the search term
        return user.getFirstname().toLowerCase().contains(recherche.toLowerCase())
                || user.getLastname().toLowerCase().contains(recherche.toLowerCase())
                || user.getLogin().toLowerCase().contains(recherche.toLowerCase());
    }
    
    @FXML
    private void handleLogOutButton() {
    	Stage currentStage = (Stage) rootAnchorPane.getScene().getWindow();
    	  
    	
        currentStage.close();
    	MainController.getInstance().showAuthenticationUI(null);
    }

    private void showWarningAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
