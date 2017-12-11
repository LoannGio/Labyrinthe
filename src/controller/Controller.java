package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Labyrinthe;
import model.Vertex;
import model.direction;
import view.View;

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
		for (int i = 0; i < 10; i++) {
			model.opendDoorRandom();
		}
		model.getPackman().startPosition(model, model.getG().getEqualVertex(v));
		model.getGhost().startPosition(model, model.getG().getEqualVertex(v));
	}

	public static Controller getInstance() {
		return instance;
	}

	public void start(Stage primaryStage) {
		view.start(primaryStage, model);
		primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
				case Z:
					model.getPackman().move(model, direction.North);
					break;
				case LEFT:
				case Q:
					model.getPackman().move(model, direction.West);
					break;
				case DOWN:
				case S:
					model.getPackman().move(model, direction.South);
					break;
				case RIGHT:
				case D:
					model.getPackman().move(model, direction.East);
					break;
				default:
					break;
				}
				view.updatePlayer(model.getPackman().getPosition().getX(), model.getPackman().getPosition().getY());
			}
		});
	}
}
