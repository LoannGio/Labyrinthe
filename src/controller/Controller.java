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
	private int level;

	private Controller() {
		view = View.getInstance();
		level = 1;
		playGame();

	}

	public static Controller getInstance() {
		return instance;
	}

	public void start(Stage primaryStage) {
		view.start(primaryStage, model);

		// Thread qui gère les déplacements de Pacman et Ghost via un timer
		MyThread t = new MyThread("timer", model, view, instance, primaryStage);

		// Récupération des évènements du clavier
		primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
				case Z:
					model.setCurrent_dir(direction.North);
					view.updateArrowUp();
					break;
				case LEFT:
				case Q:
					model.setCurrent_dir(direction.West);
					view.updateArrowLeft();
					break;
				case DOWN:
				case S:
					model.setCurrent_dir(direction.South);
					view.updateArrowDown();
					break;
				case RIGHT:
				case D:
					model.setCurrent_dir(direction.East);
					view.updateArrowRight();
					break;
				default:
					break;
				}
			}
		});
	}

	public void playGame() {
		// Creation du modele : on place la porte de sortie et on crée notre
		// arbre en prenant l'emplacement de la porte comme racine
		model = new Labyrinthe();
		model.getExit().startPosition();
		Vertex v = model.getExit().getPosition();
		model.getG().addVertex(v);
		model.buildPath(v);

		// "Ouvertures de portes" afin de rendre le jeu imparfait
		int nbDoors = 100 - 10 * level;

		if (nbDoors < 0)
			nbDoors = 0;
		for (int i = 0; i < nbDoors; i++) {
			model.openDoorRandom();
		}

		// On place le Packman à l'endroit le plus eloigne de la porte dans
		// l'arbre
		model.getPackman().startPosition(model, model.getG().getEqualVertex(v));

		// On place le Ghost et les bonbons de manière aléatoire, avec une
		// contrainte de distance minimum avec le packman
		model.getGhost().startPosition(model, model.getG().getEqualVertex(v));
		for (int j = 0; j < 4; j++) {
			model.getBonbons().get(j).startPosition(model, model.getPackman().getPosition());
		}
	}

	public void continueGame(Stage primaryStage) {
		// Creation du modele : on place la porte de sortie et on crée notre
		// arbre en prenant l'emplacement de la porte comme racine
		model = new Labyrinthe();
		model.getExit().startPosition();
		Vertex v = model.getExit().getPosition();
		model.getG().addVertex(v);
		model.buildPath(v);

		// On augmente le niveau de difficulté
		level++;

		// "Ouvertures de portes" afin de rendre le jeu imparfait en tenant
		// compte du nivea
		int nbDoors = 100 - 10 * level;
		for (int i = 0; i < nbDoors; i++) {
			model.openDoorRandom();
		}

		// On place le Ghost et les bonbons de manière aléatoire, avec une
		// contrainte de distance minimum avec le packman
		model.getPackman().startPosition(model, model.getG().getEqualVertex(v));
		model.getGhost().startPosition(model, model.getG().getEqualVertex(v));
		for (int j = 0; j < 4; j++) {
			model.getBonbons().get(j).startPosition(model, model.getPackman().getPosition());
		}

		// On relance la vue
		this.start(primaryStage);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
