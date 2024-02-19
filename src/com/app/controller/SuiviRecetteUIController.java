package com.app.controller;

import com.app.dao.impl.PaiementDaoImpl;
import com.app.exception.DAOException;
import com.app.models.Paiement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.List;

public class SuiviRecetteUIController {

    @FXML
    private VBox rootVBox;

    @FXML
    private ChoiceBox<String> filterChoiceBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableView<Paiement> recetteTableView;

    @FXML
    private TableColumn<Paiement, LocalDate> dateColumn;

    @FXML
    private TableColumn<Paiement, Double> montantColumn;

    @FXML
    private BarChart<String, Double> recetteBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private PaiementDaoImpl paiementDao;

    @FXML
    private void initialize() {
        paiementDao = new PaiementDaoImpl();

        filterChoiceBox.getItems().addAll("Tous", "Aujourd'hui", "Cette semaine", "Ce mois");
        filterChoiceBox.setValue("Tous");

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date_De_Paiement"));

        montantColumn.setCellValueFactory(new PropertyValueFactory<>("Montant_TotalCommande"));

        recetteTableView.setItems(getRecettes());

        xAxis.setLabel("Période");
        yAxis.setLabel("Montant");

        updateRecetteBarChart();
    }

    private ObservableList<Paiement> getRecettes() {
        try {
            return FXCollections.observableArrayList(paiementDao.list());
        } catch (DAOException e) {
            e.printStackTrace(); // Ajoutez cette ligne pour afficher l'exception dans la console
            showAlert("Erreur", "Une erreur est survenue lors du chargement des recettes.", Alert.AlertType.ERROR);
            return FXCollections.observableArrayList();
        }
    }


    private void updateRecetteBarChart() {
        recetteBarChart.getData().clear();

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName("Recettes");

        List<Paiement> recettes = getRecettes();
        double totalRecettes = 0.0;

        for (Paiement paiement : recettes) {
            totalRecettes += paiement.getMontant_TotalCommande();
        }

        series.getData().add(new XYChart.Data<>("Total", totalRecettes));

        recetteBarChart.getData().add(series);
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
