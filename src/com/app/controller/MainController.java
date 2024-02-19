package com.app.controller;

import java.io.IOException;

import com.app.models.Commande;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {

	/***
	 * Declarations des fenetres de l'application
	 */
	private AnchorPane authenticationUI;
	private AnchorPane launchUI;
	
	/*
	 * Fenetre en cours 
	 * **/
	private Stage primaryStage;
	
	// Creation instance MainController
	private static MainController instance = null ;
	

	
	// static views
	private static String VIEW_PATH= "/com/app/controller/";
	
	private GestionUtilisateursUIController gestionUtilisateursUIController = null;
	private ManageProductUIController manageProduct = null;
	private MenuUIController menuUI = null;
	
    
    /***
     * Afficher la fenetre de lancement
     */
    public void showLaunchUI(Stage stage) {
        try {
        	
        	// Load root layout from fxml file.
        	launchUI = (AnchorPane) FXMLLoader.load(getClass().getResource(VIEW_PATH+"LaunchUI.fxml"));

        	// Show the scene containing the root layout.
        	Scene scene = new Scene(launchUI);
        	this.primaryStage = stage;
        	this.primaryStage.setScene(scene);
        	this.primaryStage.setTitle("Restaurant Management System");
        	this.primaryStage.setResizable(false);
        	this.primaryStage.show();

        } catch (IOException e) { 
        	e.printStackTrace(); 
        }
    }
    
    /***
	 * Afficher la fenetre d'authentification
	 */
    public void showAuthenticationUI(Stage stage) {
    	try {
        	authenticationUI = (AnchorPane) FXMLLoader.load(getClass().getResource(VIEW_PATH+"AuthUI.fxml"));
        	Scene scene = new Scene(authenticationUI);
        	this.primaryStage = stage;
        	this.primaryStage.setTitle("Authentication");
        	this.primaryStage.setScene(scene);
        	this.primaryStage.show();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    
    /***
	 * Afficher la fenetre de gestion des Utilisateurs
	 */
    public void showGestionUtilisateursWindow(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_PATH + "GestionUtilisateursUI.fxml"));
            Parent root = loader.load();
            gestionUtilisateursUIController = loader.getController();
            // AnchorPane gestionUtilisateursUI =  (AnchorPane) FXMLLoader.load(getClass().getResource(VIEW_PATH + "GestionUtilisateursUI.fxml"));
            Scene scene = new Scene(root);
            
            Stage s = new Stage();
            
            //this.primaryStage = stage;
            s.setScene(scene);
            s.setTitle("Gestion des Utilisateurs");
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /***
   	 * Afficher la fenetre de gestion des Produits
   	 */
       public void showGestionProduitsWindow(Stage stage) {
           try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_PATH + "ManageProductUI.fxml"));
               Parent root = loader.load();
               manageProduct= loader.getController();
               //AnchorPane ManageProductUI =  (AnchorPane) FXMLLoader.load(getClass().getResource(VIEW_PATH + "ManageProductUI.fxml"));
               Scene scene = new Scene(root);
               
               Stage s = new Stage();
               
               //this.primaryStage = stage;
               s.setScene(scene);
               s.setTitle("Gestion des Produits");
               s.show();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       
       public void showMenuWindow(Stage Stage, Commande commande) {
           try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_PATH + "MenuUI.fxml"));
               Parent root = loader.load();
               menuUI = loader.getController();
               menuUI.setCommande(commande);
                Scene scene = new Scene(root);
               
               Stage s = new Stage();
               
              
               s.setScene(scene);
               s.setTitle("Page de Commande");
               s.show();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    
       
       public void showPriseCommandeWindow(Stage stage) {
   	    try {
   	        FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_PATH + "PriseCommande.fxml"));
   	        Parent root = loader.load();
   	        Scene scene = new Scene(root);

   	        Stage s = new Stage();

   	        s.setScene(scene);
   	        s.setTitle("Prise de Commande");
   	        s.initModality(Modality.WINDOW_MODAL); 
   	        s.initOwner(stage);
   	        s.showAndWait();
   	    } catch (IOException e) {
   	        e.printStackTrace();
   	    }
   	}
       
       public void openSuiviRecetteWindow() {
    	    try {
    	        FXMLLoader loader = new FXMLLoader(getClass().getResource("SuiviRecetteUI.fxml"));
    	        Parent root = loader.load();
    	        
    	        SuiviRecetteUIController controller = loader.getController();

    	        Stage stage = new Stage();
    	        stage.initModality(Modality.APPLICATION_MODAL);
    	        stage.setTitle("Suivi des Recettes");
    	        stage.setScene(new Scene(root));
    	        stage.showAndWait();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }}

      
    public static MainController getInstance() {
    	if (instance == null) instance =  new MainController();
		return instance;
		
	}
}