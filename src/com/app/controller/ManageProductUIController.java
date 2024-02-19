package com.app.controller;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.app.dao.impl.ProduitDaoImpl;
import com.app.enums.Role;
import com.app.exception.DAOException;
import com.app.metier.UserMetier;
import com.app.models.*;
import com.app.util.ImageTableCell;

public class ManageProductUIController {

	@FXML
    private StackPane rootStackPane;
	
    @FXML
    private ImageView ImportImageView;
    
    @FXML
    private ComboBox StatusComboBox;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnImport;

    @FXML
    private Button btnUpdate;
    
    @FXML
    private Button btnLogOut;
    
    @FXML
	private TextField IDTextField;
    
    
    @FXML
    private TextField SearchtxtField;
    
	@FXML
	private TextField NameTextField;
	@FXML
	private TextField DescriptionTextField;
	@FXML
	private TextField PriceTextField;
	
	@FXML
	private TextField QuantityTextField;
    
	
	 @FXML
	    private TableView<Produit> ProduitsTable;
	 
    @FXML
    private TableColumn<Produit, String> col_Name;

    @FXML
    private TableColumn<Produit, Integer> col_ID;

    @FXML
    private TableColumn<Produit, Double> col_Price;

    @FXML
    private TableColumn<Produit, Integer> col_Quantity;

    @FXML
    private TableColumn<Produit, String> col_Description;
    
    @FXML
    private TableColumn<Produit, Image> col_Image;
    

    @FXML
    private AnchorPane main_form;
    
    private Stage dialogStage;
	private Produit produit;
	private ProduitDaoImpl produitDao;
    
     
    
    @FXML
    public void initialize( ) throws DAOException {
    	
    	 produitDao = new ProduitDaoImpl();
    	 
    	col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));

        col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
         col_Price.setCellValueFactory(new PropertyValueFactory<>("price"));
         col_Description.setCellValueFactory(new PropertyValueFactory<>("description"));
         col_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
 		TableColumn<Produit, byte[]> col_Image = new TableColumn<>("col_Image");
 		col_Image.setCellValueFactory(new PropertyValueFactory<>("imageBytes"));
 		col_Image.setCellFactory(col -> new ImageTableCell<>()); // Utilisation de la cellule personnalisée

    

         ProduitsTable.getItems().addAll(produitDao.list());
    	
		
    }
    
    public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
    
    public void setProduit(Produit produit) {
		this.produit = produit;

		IDTextField.setText(Integer.toString(produit.getId()));
		NameTextField.setText(produit.getName());
		DescriptionTextField.setText(produit.getDescription());
		PriceTextField.setText(Double.toString(produit.getPrice()));
		QuantityTextField.setText(Integer.toString(produit.getQuantity()));
		
	}
    
   


    	 private boolean isInputValid() { // Validates the produit input in the text fields.
    		 String errorMessage = "";

    		 if (NameTextField.getText() == null || NameTextField.getText().length() == 0) 
    			 errorMessage += "Le nom n'est pas renseigné !\n"; 

    		 if (errorMessage.length() == 0) return true;
    		 else { // Show the error message.            
    			 Alert alert = new Alert(AlertType.ERROR);
    			// alert.initOwner(dialogStage);
    			 alert.setTitle("Champs non renseignés et/ou invalides !");
    			 alert.setHeaderText("Veuillez remplir tous les champs svp !");
    			 alert.setContentText(errorMessage);

    			 alert.showAndWait();
    			 return false;
    		 }
    	 }
    	 
    	 @FXML
    	 private void handleImportButton() throws DAOException {
    	     FileChooser fileChooser = new FileChooser();
    	     fileChooser.getExtensionFilters().addAll(
    	         new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
    	     );

    	     File selectedFile = fileChooser.showOpenDialog(null);

    	     if (selectedFile != null) {
    	         try {
    	             byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());

    	             if (produit == null) {
    	                 produit = new Produit();
    	             }

    	             produit.setImage(imageBytes); // Enregistrez les données binaires de l'image dans l'entité Produit

    	             // Affichez l'image importée dans ImageView
    	             Image importedImage = new Image(selectedFile.toURI().toString());
    	             ImportImageView.setImage(importedImage);

    	         } catch (IOException e) {
    	             e.printStackTrace();
    	         }
    	     }
    	 }

    	 @FXML
    	 private void handleAddButton() throws DAOException {
    	     if (isInputValid()) {
    	         if (produit == null) {
    	             produit = new Produit();
    	         }

    	         produit.setId(Integer.parseInt(IDTextField.getText()));
    	         produit.setName(NameTextField.getText());
    	         produit.setDescription(DescriptionTextField.getText());
    	         produit.setPrice(Double.parseDouble(PriceTextField.getText()));
    	         produit.setQuantity(Integer.parseInt(QuantityTextField.getText()));

    	         if (produit.getImage() == null) {
    	             // L'image n'a pas été importée
    	             Alert alert = new Alert(Alert.AlertType.WARNING);
    	             alert.setTitle("Avertissement");
    	             alert.setHeaderText("L'image du produit n'a pas été importée !");
    	             alert.setContentText("Veuillez importer l'image du produit avant d'ajouter.");

    	             alert.showAndWait();
    	             return; 
    	         }

    	         produitDao.create(produit);

    	         // Ajoutez le produit à la liste des produits dans le TableView
    	         ProduitsTable.getItems().add(produit);

    	         // Réinitialisez les champs de texte et d'autres entrées si nécessaire
    	         clearInputFields();
    	     } else {
    	         System.out.println("Validation failed!");
    	     }
    	 }

    	


    	 
    	 private void clearInputFields() {
    		    IDTextField.clear();
    		    NameTextField.clear();
    		    DescriptionTextField.clear();
    		    PriceTextField.clear();
    		    QuantityTextField.clear();
    		    ImportImageView.setImage(null);

    		}
    	 
    	 

    	 @FXML
    	 private void handleClearButton() {
    	     clearInputFields(); // Appeler la méthode pour vider les champs de saisie
    	     ImportImageView.setImage(null); // Effacer l'image affichée dans l'ImageView
    	 }

    	 @FXML
    	 private void handleLogOutButton() {
    	     Stage currentStage = (Stage) rootStackPane.getScene().getWindow();
    	     currentStage.close();
    	     
    	     // Appel à la méthode pour afficher l'interface d'authentification
    	     MainController.getInstance().showAuthenticationUI(null);
    	 }

    	 @FXML
    	 private void handleDeleteButton() throws DAOException {
    	     Produit selectedProduit = ProduitsTable.getSelectionModel().getSelectedItem();
    	     if (selectedProduit != null) {
    	         produitDao.delete(selectedProduit.getId()); // Supprimer le produit de la base de données par son ID
    	         ProduitsTable.getItems().remove(selectedProduit); // Supprimer le produit de la TableView
    	     } else {
    	         showAlert("Sélection nécessaire", "Sélectionnez un produit à supprimer.", Alert.AlertType.WARNING);
    	     }
    	 }

    	 
    	 
    	
    	 private boolean showUpdateDialog(Produit produit) {
    		    // Créez une nouvelle boîte de dialogue ou une fenêtre modale
    		    Dialog<Boolean> dialog = new Dialog<>();
    		    dialog.setTitle("Modifier le produit");
    		    dialog.setHeaderText(null);

    		    // Créez des champs de texte pour les détails du produit
    		    TextField idTextField = new TextField(Integer.toString(produit.getId()));
    		    TextField nameTextField = new TextField(produit.getName());
    		    TextField descriptionTextField = new TextField(produit.getDescription());
    		    TextField priceTextField = new TextField(Double.toString(produit.getPrice()));
    		    TextField quantityTextField = new TextField(Integer.toString(produit.getQuantity()));

    		    // Ajoutez les champs de texte à un GridPane
    		    GridPane grid = new GridPane();
    		    grid.add(new Label("ID :"), 0, 0);
    		    grid.add(idTextField, 1, 0);
    		    grid.add(new Label("Nom :"), 0, 1);
    		    grid.add(nameTextField, 1, 1);
    		    grid.add(new Label("Description :"), 0, 2);
    		    grid.add(descriptionTextField, 1, 2);
    		    grid.add(new Label("Prix :"), 0, 3);
    		    grid.add(priceTextField, 1, 3);
    		    grid.add(new Label("Quantité :"), 0, 4);
    		    grid.add(quantityTextField, 1, 4);

    		    dialog.getDialogPane().setContent(grid);

    		    // Ajoute les boutons de confirmation et d'annulation
    		    ButtonType confirmButtonType = new ButtonType("Modifier", ButtonBar.ButtonData.OK_DONE);
    		    dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

    		    // Attend que l'utilisateur termine la modification et renvoyez le résultat
    		    dialog.setResultConverter(dialogButton -> {
    		        if (dialogButton == confirmButtonType) {
    		            // Mettre à jour les valeurs du produit avec celles des champs de texte
    		            produit.setId(Integer.parseInt(idTextField.getText()));
    		            produit.setName(nameTextField.getText());
    		            produit.setDescription(descriptionTextField.getText());
    		            produit.setPrice(Double.parseDouble(priceTextField.getText()));
    		            produit.setQuantity(Integer.parseInt(quantityTextField.getText()));

    		            return true; // Modification réussie
    		        }
    		        return false; // Annulation
    		    });

    		    Optional<Boolean> result = dialog.showAndWait();
    		    return result.orElse(false); // Renvoie true si la modification a été effectuée avec succès
    		}

    	 
    	 @FXML
    	 private void handleModifierButton() throws DAOException {
    	     Produit selectedProduit = ProduitsTable.getSelectionModel().getSelectedItem();
    	     if (selectedProduit != null) {
    	         boolean isUpdated = showUpdateDialog(selectedProduit);

    	         if (isUpdated) {
    	             produitDao.update(selectedProduit);
    	             ProduitsTable.refresh(); 
    	         }
    	     } else {
    	         showAlert("Sélection nécessaire", "Sélectionnez un produit à modifier.", Alert.AlertType.WARNING);
    	     }
    	 }

    	 
    	 @FXML
    	 private void handleTextFieldSearch() throws DAOException {
    	     String searchTerm = SearchtxtField.getText().trim().toLowerCase();

    	     if (searchTerm.isEmpty()) {
    	      
    	         ProduitsTable.setItems(FXCollections.observableArrayList(produitDao.list()));
    	     } else {
    	    
    	         List<Produit> filteredProduits = produitDao.list().stream()
    	                 .filter(produit ->
    	                         String.valueOf(produit.getId()).toLowerCase().contains(searchTerm) ||
    	                         produit.getName().toLowerCase().contains(searchTerm) ||
    	                         String.valueOf(produit.getQuantity()).toLowerCase().contains(searchTerm) ||
    	                         String.valueOf(produit.getPrice()).toLowerCase().contains(searchTerm) ||
    	                         produit.getDescription().toLowerCase().contains(searchTerm))
    	                 .collect(Collectors.toList());

    	         ProduitsTable.setItems(FXCollections.observableArrayList(filteredProduits));
    	     }
    	 }

    	    
    	

    	 


    private static Scene getScene() {
			// TODO Auto-generated method stub
			return getScene();
		}

	// Méthode utilitaire pour afficher des alertes
    private void showAlert(String title, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();

}}
