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
		view = View.getInstance();
		model = new Labyrinthe();
		model.getExit().startPosition();
		Vertex v = model.getExit().getPosition();
		model.getG().addVertex(v);
		model.buildPath(v);
		for (int i = 0; i < 10; i++) {
			model.openDoorRandom();
		}
		model.getPackman().startPosition(model, model.getG().getEqualVertex(v));
		model.getGhost().startPosition(model, model.getG().getEqualVertex(v));
		model.getBonbon().startPosition(model,model.getPackman().getPosition());
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
					model.getGhost().move(model);
					break;
				case LEFT:
				case Q:
					model.getPackman().move(model, direction.West);
					model.getGhost().move(model);
					break;
				case DOWN:
				case S:
					model.getPackman().move(model, direction.South);
					model.getGhost().move(model);
					break;
				case RIGHT:
				case D:
					model.getPackman().move(model, direction.East);
					model.getGhost().move(model);
					break;
				default:
					break;
				}
				
				view.updatePlayer(model.getPackman().getPosition().getX(), model.getPackman().getPosition().getY());
				view.updateGhost(model.getGhost().getPosition().getX(), model.getGhost().getPosition().getY());	
				int end = model.checkCollision(); // -1 <=> pas fini ; 0 <=> perdu ; 1 <=> gagné ; 2 <=> bonbon ramassé
				int doNext = -2; //-1 <=> rejouer ; 0 <=> quitter ; 1 <=> continuer ; -2 <=> partie non-finie
				if(end == 0 || end == 1)
					doNext = view.drawEndGame(end);
				//else if(end == 2)
					
				if(doNext != -2) {
					switch(doNext) {
					case -1:
						replay(primaryStage);
						break;
						
					case 0:
						System.exit(0);
						break;
						
					case 1:
						break;
						
					default:
						break;
					}
				}
			}
		});
	}
	
	private void replay(Stage primaryStage) {
		model = new Labyrinthe();
		model.getExit().startPosition();
		Vertex v = model.getExit().getPosition();
		model.getG().addVertex(v);
		model.buildPath(v);
		for (int i = 0; i < 20; i++) {
			model.openDoorRandom();
		}
		model.getPackman().startPosition(model, model.getG().getEqualVertex(v));
		model.getGhost().startPosition(model, model.getG().getEqualVertex(v));
		this.start(primaryStage);
	}
}
