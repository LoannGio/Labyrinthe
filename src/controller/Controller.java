package controller;

import javafx.stage.Stage;
import model.Model;
import view.View;

public class Controller {
	
	private static Controller instance = new Controller();
	private Model model;
	private View view;
	
	private Controller() {
		//model = Model.getInstance();
		view = View.getInstance();
	}
	
	public static Controller getInstance() {
		return instance;
	}
	
	public void start(Stage primaryStage) {
		view.start(primaryStage);
	};

}
