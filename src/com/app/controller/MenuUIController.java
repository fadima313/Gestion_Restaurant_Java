package com.app.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.app.dao.impl.ProduitDaoImpl;
import com.app.dao.impl.UserDaoImpl;
import com.app.exception.DAOException;
import com.app.models.*;
import com.app.dao.impl.CommandeDaoImpl;
import com.app.dao.impl.PaiementDaoImpl;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuUIController {

	
	 private MainController mainController;

	    public void setMainController(MainController mainController) {
	        this.mainController = mainController;
	    } 
	    
	    private Commande commande;

	    public void setCommande(Commande commande) {
	        this.commande = commande;
	    }

	    public Commande getCommande() {
	        return commande;
	    }
	    private double montantPayer = 0.0;

	    @FXML
	    
	    private AnchorPane rootAnchorPane;
	    
	@FXML
	private TableView<Produit> CommandeProduit_tableview;


	    @FXML
	    private TableColumn<Produit, String> Description_Col;

	    @FXML
	    private TableColumn<Produit, Double> Price_Col;

	    @FXML
	    private TableColumn<Produit, Integer> ProductID_Col;

	    @FXML
	    private TableColumn<Produit, String> ProductName_Col;

	    @FXML
	    private TableView<Produit> TableViewProduit;


	    @FXML
	    private Label StatutLabel;
	    private boolean paymentCompleted = false;

	    @FXML
	    private TableColumn<Produit, Double> menu_col_price;

	    @FXML
	    private TableColumn<Produit, String> menu_col_productName;

	    @FXML
	    private TableColumn<Produit, Integer> menu_col_quantity;

	    @FXML
	    private Button menu_paybtn;
	    
	    
	    
	    @FXML
	    private Button Commande_Enregistrer;
	    

	    @FXML
	    private Button btnAjouterProduitToCommande;

	    @FXML
	    private Button btnVoirRecette;
	   

	    @FXML
	    private Button menu_remove_btn;

	    @FXML
	    private Label menu_total;
	    
	    private PaiementDaoImpl paiementDao;
	   // private UserDaoImpl userDao = new UserDaoImpl();

	    CommandeDaoImpl commandeDao = new CommandeDaoImpl(); 
	    
	    @FXML
	    private void initialize() {
	        ProduitDaoImpl produitDao = new ProduitDaoImpl();
	        
	        try {
	            ObservableList<Produit> produitsList = FXCollections.observableArrayList(produitDao.list());
	            
	           
	            TableViewProduit.setItems(produitsList);
	            ProductID_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
	            ProductName_Col.setCellValueFactory(new PropertyValueFactory<>("name"));
	            Description_Col.setCellValueFactory(new PropertyValueFactory<>("description"));
	            Price_Col.setCellValueFactory(new PropertyValueFactory<>("price"));
	            
	        } catch (DAOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	  

	    @FXML
	    private void handleAjouterProduitToCommande() {
	        Produit selectedProduit = TableViewProduit.getSelectionModel().getSelectedItem();
	        
	        if (selectedProduit != null) {
	            TextInputDialog dialog = new TextInputDialog();
	            dialog.setTitle("Ajouter Produit à la Commande");
	            dialog.setHeaderText("Saisissez la quantité du produit :");
	            dialog.setContentText("Quantité:");

	            Optional<String> result = dialog.showAndWait();
	            
	            if (result.isPresent()) {
	                try {
	                    int quantite = Integer.parseInt(result.get());
	                    
	                    selectedProduit.setQuantity(quantite); 
	                    CommandeProduit_tableview.getItems().add(selectedProduit);
	                    
	                    menu_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
	                    menu_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
	                    menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
	                    
	                    
	                    updateTotal();
	                } catch (NumberFormatException e) {
	                    showAlert("Erreur", "Veuillez saisir une quantité valide.", Alert.AlertType.ERROR);
	                }
	            }
	        } else {
	            showAlert("Sélection nécessaire", "Sélectionnez un produit à ajouter à la commande.", Alert.AlertType.WARNING);
	        }
	    }

	    @FXML
	    private void handleCommandeEnregistrerButton() {
	        if (commande != null && commande.getClient() != null) {
	            // Associer le client à la commande (déjà fait lorsque le client est créé)
	        	
	        	 ObservableList<Produit> produitsSelectionnes = CommandeProduit_tableview.getItems();
	             commande.getProduits().addAll(produitsSelectionnes);
	             
	             
	            // Enregistrer la commande avec les informations associées
	            CommandeDaoImpl commandeDao = new CommandeDaoImpl();
	          
	          

	            
	            try {
	                commandeDao.create(commande);
	                showAlert("Succès", "La commande a été enregistrée avec succès.", Alert.AlertType.INFORMATION);
	            } catch (DAOException e) {
	                showAlert("Erreur", "Une erreur est survenue lors de l'enregistrement de la commande.", Alert.AlertType.ERROR);
	            }
	        } else {
	            showAlert("Erreur", "La commande ou le client n'est pas correctement configuré.", Alert.AlertType.ERROR);
	        }
	    }


	   
	    private void updateTotal() {
	        double total = 0;
	        for (Produit produit : CommandeProduit_tableview.getItems()) {
	            total += produit.getPrice() * produit.getQuantity();
	        }
	        
	        commande.setTotalPrice(total); // Mettre à jour le totalPrice de la commande
	        
	        menu_total.setText("" + total + "FCFA");
	    }

	    

	    
	    @FXML
	    private void handlePayButton() {
	    	System.out.println("Methode Payement commencer");
	    	
	    	if (commande != null) {
	        if (!paymentCompleted) {
	            TextInputDialog montantPayerDialog = new TextInputDialog();
	            montantPayerDialog.setTitle("Montant du Paiement");
	            montantPayerDialog.setHeaderText("Entrez le montant payé :");
	            montantPayerDialog.setContentText("Montant:");

	            Optional<String> montantPayerResult = montantPayerDialog.showAndWait();

	            if (montantPayerResult.isPresent()) {
	                try {
	                    double montantPayer = Double.parseDouble(montantPayerResult.get());
	                    double montantTotal = getTotalFromCommande(); 
	                    double monnaieRendue = montantPayer - montantTotal;

	                    Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
	                    confirmAlert.setTitle("Confirmation");
	                    confirmAlert.setHeaderText(null);
	                    confirmAlert.setContentText("Êtes-vous sûr de vouloir confirmer le paiement ?\nMontant payé : " + montantPayer + " FCFA\nMonnaie rendue : " + monnaieRendue + " FCFA");

	                    Optional<ButtonType> result = confirmAlert.showAndWait();
	                    if (result.isPresent() && result.get() == ButtonType.OK) {
	                        Date dateDeCommande = commande.getDateCommande();
	                        Paiement paiement = new Paiement(montantTotal, montantPayer, monnaieRendue, dateDeCommande, new ArrayList<>(), "Terminé"); // Assurez-vous de passer l'état approprié du paiement
	                        PaiementDaoImpl paiementDao = new PaiementDaoImpl();
	                        paiementDao.create(paiement);
	                        commande.setPaiement(paiement);

	                        commandeDao.update(commande);
	                        
	                        paymentCompleted = true;
	                        StatutLabel.setText("Terminé");

	                        Alert paymentAlert = new Alert(Alert.AlertType.INFORMATION);
	                        paymentAlert.setTitle("Confirmation de paiement");
	                        paymentAlert.setHeaderText(null);
	                        paymentAlert.setContentText("Le paiement a été effectué avec succès !");

	                        paymentAlert.showAndWait();
	                    }
	                } catch (NumberFormatException e) {
	                    showAlert("Erreur", "Veuillez saisir un montant valide.", Alert.AlertType.ERROR);
	                } catch (DAOException e) {
	                    showAlert("Erreur de base de données", "Une erreur est survenue lors de l'enregistrement du paiement.", Alert.AlertType.ERROR);
	                }
	            }}}
	        
	    }
	    
	    private double getTotalFromCommande() {
	        double total = 0;
	        for (Produit produit : CommandeProduit_tableview.getItems()) {
	            total += produit.getPrice() * produit.getQuantity();
	        }
	        return total;
	    }


	    @FXML
	    private void handleRemoveButton() {
	        Produit selectedProduit = CommandeProduit_tableview.getSelectionModel().getSelectedItem();

	        if (selectedProduit != null) {
	            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
	            confirmAlert.setTitle("Confirmation de suppression");
	            confirmAlert.setHeaderText(null);
	            confirmAlert.setContentText("Êtes-vous sûr de vouloir supprimer ce produit de la commande ?");

	            Optional<ButtonType> result = confirmAlert.showAndWait();
	            if (result.isPresent() && result.get() == ButtonType.OK) {
	                try {
	                    CommandeDaoImpl commandeDao = new CommandeDaoImpl();
	                    commandeDao.removeProduitFromCommande(commande, selectedProduit);

	                    CommandeProduit_tableview.getItems().remove(selectedProduit);
	                    updateTotal();
	                } catch (DAOException e) {
	                    showAlert("Erreur de base de données", "Une erreur est survenue lors de la suppression du produit de la commande.", Alert.AlertType.ERROR);
	                }
	            }
	        } else {
	            Alert noSelectionAlert = new Alert(Alert.AlertType.WARNING);
	            noSelectionAlert.setTitle("Aucune sélection");
	            noSelectionAlert.setHeaderText(null);
	            noSelectionAlert.setContentText("Sélectionnez un produit à supprimer de la commande.");

	            noSelectionAlert.showAndWait();
	        }
	    }
	    
	    @FXML
	    private void handleVoirRecetteButton() {
	        MainController.getInstance().openSuiviRecetteWindow();
	    }

	    @FXML
   	 private void handleLogOutButton() {
   	     Stage currentStage = (Stage) rootAnchorPane.getScene().getWindow();
   	     currentStage.close();
   	     
   	     // Appel à la méthode pour afficher l'interface d'authentification
   	     MainController.getInstance().showAuthenticationUI(null);
   	 }

	        
	    private void showAlert(String title, String content, Alert.AlertType alertType) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }

}



