package view;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Bonbon;
import model.Exit;
import model.Ghost;
import model.Graph;
import model.Labyrinthe;
import model.Packman;
import model.Vertex;

public class View {

	private static View instance = new View();

	private View() {

	}

	public static View getInstance() {
		return instance;
	}

	public void start(Stage primaryStage, Labyrinthe laby) {
		primaryStage.setTitle("Labyrinthe");
		int longueur = laby.getRIGHT_BORDER() + 1;
		int largeur = laby.getDOWN_BORDER() + 1;
		ViewFrame.drawFrame(primaryStage, longueur, largeur);
		Graph graph = laby.getG();
		Packman player = laby.getPackman();
		Ghost ghost = laby.getGhost();
		Exit exit = laby.getExit();
		Bonbon bonbon = laby.getBonbon();

		// Walls initialization
		for (int i = 0; i < longueur; i++) {
			for (int j = 0; j < largeur; j++) {
				ViewFrame.drawWall(i, j, i + 1, j, ViewFrame.WALL_COLOR);
				ViewFrame.drawWall(i, j, i, j + 1, ViewFrame.WALL_COLOR);
			}
		}

		for (int i = 0; i < longueur; i++) {
			for (int j = 0; j < largeur; j++) {
				Vertex v = graph.getEqualVertex(new Vertex(i, j));
				Vertex vUp = graph.getEqualVertex(new Vertex(i, j - 1));
				Vertex vLeft = graph.getEqualVertex(new Vertex(i - 1, j));
				Vertex vRight = graph.getEqualVertex(new Vertex(i + 1, j));
				Vertex vDown = graph.getEqualVertex(new Vertex(i, j + 1));

				if (graph.containsEdge(v, vUp) && vUp != null)
					ViewFrame.drawWall(v.getX(), v.getY(), vUp.getX(), vUp.getY(), ViewFrame.SCENE_COLOR);
				if (graph.containsEdge(v, vLeft) && vLeft != null)
					ViewFrame.drawWall(v.getX(), v.getY(), vLeft.getX(), vLeft.getY(), ViewFrame.SCENE_COLOR);
				if (graph.containsEdge(v, vRight) && vRight != null)
					ViewFrame.drawWall(v.getX(), v.getY(), vRight.getX(), vRight.getY(), ViewFrame.SCENE_COLOR);
				if (graph.containsEdge(v, vDown) && vDown != null)
					ViewFrame.drawWall(v.getX(), v.getY(), vDown.getX(), vDown.getY(), ViewFrame.SCENE_COLOR);
			}
		}

		ViewFrame.drawPlayer(player.getPosition().getX(), player.getPosition().getY());
		ViewFrame.drawExit(exit.getPosition().getX(), exit.getPosition().getY());
		ViewFrame.drawCandy(bonbon.getPosition().getX(), bonbon.getPosition().getY());
		ViewFrame.drawGhost(ghost.getPosition().getX(), ghost.getPosition().getY());
	}

	public void startGame(Stage primaryStage, Labyrinthe laby) {

	}

	public void updatePlayer(int x, int y) {
		ViewFrame.updatePlayer(x, y);
	}

	public void updateGhost(int x, int y) {
		ViewFrame.updateGhost(x, y);
	}

	public void pickUpBonbon(int score) {
		ViewFrame.deleteBonbon(score);
	}

	public void setOnAction(EventHandler<KeyEvent> event) {
		ViewFrame.scene.setOnKeyPressed(event);
	}

	public void updateTime(int temps) {
		ViewFrame.updateTime(temps);
	}
	
	public void updateScore(int score) {
		ViewFrame.updateScore(score);
	}
	
	public void updateLevel(int level) {
		ViewFrame.updateLevel(level);
	}

	public int drawEndGame(int end) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		String title = "Gagne !";
		String text = "Bravo, vous avez gagne !";
		String replay = "Continuer";
		if (end == 0) {
			title = "Perdu...";
			text = "Dommage, essayez encore une fois !";
			replay = "Rejouer";
		}
		ButtonType buttonTypeReplay = new ButtonType(replay);
		ButtonType buttonTypeQuit = new ButtonType("Quitter");
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(text);

		alert.getButtonTypes().setAll(buttonTypeReplay, buttonTypeQuit);
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == buttonTypeReplay) {
			if (end == 0) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return 0;
		}
	}
}
