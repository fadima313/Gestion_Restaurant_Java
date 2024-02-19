package com.app;

import java.io.IOException;

import com.app.controller.MainController;
import com.app.dao.impl.UserDaoImpl;
import com.app.exception.DAOException;
import com.app.metier.UserMetier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RestoApplication extends Application {

    private MainController mainController;
	private static String VIEW_PATH = "/com/app/controller/";
	private UserMetier userMetier;
    

	private static RestoApplication instance = null ;
	public static RestoApplication getInstance() {
    	
		return instance;
	}
    public MainController getMainController() {
		return mainController;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public RestoApplication() {
		try {
			mainController =  MainController.getInstance();
			userMetier = new UserMetier();
			userMetier.initAdmin();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
  
	@Override
	public void start(Stage primaryStage) {
		instance = null;
        mainController.showLaunchUI(primaryStage);
	}
    
	public static void main(String[] args) throws DAOException {
		launch(args);
		//System.out.println(userDao.list());
		
		
	}
	
	
    

}
