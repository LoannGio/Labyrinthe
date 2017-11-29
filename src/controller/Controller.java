package controller;

import javafx.stage.Stage;
import view.View;
import model.*;

public class Controller {

	private static Controller instance = new Controller();
	private View view;
	private Labyrinthe laby;

	private Controller() {
		// model = Model.getInstance();
		view = View.getInstance();
		laby = new Labyrinthe();
		laby.getDoor().startPosition();
		Vertex v = laby.getDoor().getPosition();
		laby.getG().addVertex(v);
		laby.buildPath(v);
		laby.getPackman().startPosition(laby, laby.getG().getEqualVertex(v));
	}

	public static Controller getInstance() {
		return instance;
	}

	public void start(Stage primaryStage) {
		view.start(primaryStage);
	};

}
