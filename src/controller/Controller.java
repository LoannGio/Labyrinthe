package controller;

import javafx.stage.Stage;
import view.View;
import model.*;

public class Controller {

	private static Controller instance = new Controller();
	private View view;
	private Labyrinthe model;

	private Controller() {
		// model = Model.getInstance();
		view = View.getInstance();
		model = new Labyrinthe();
		model.getExit().startPosition();
		Vertex v = model.getExit().getPosition();
		model.getG().addVertex(v);
		model.buildPath(v);
		model.getPackman().startPosition(model, model.getG().getEqualVertex(v));
		model.getGhost().startPosition(model, model.getG().getEqualVertex(v));
	}

	public static Controller getInstance() {
		return instance;
	}

	public void start(Stage primaryStage) {
		view.start(primaryStage, model);
	};

}
