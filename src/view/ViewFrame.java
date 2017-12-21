package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewFrame {
	static final int SPAN = 4; // Pixels for a unit
	static final int WALL = 2; // thickness of the walls (in units)
	static final int CELL = 9; // size of the cells (in units)
	public static final Paint WALL_COLOR = Color.BURLYWOOD;
	public static final Paint SCENE_COLOR = Color.WHITE;
	private static Group pane;
	private static Image player = new Image("img/player.png");
	private static Image door = new Image("img/door_open.png");
	private static Image ghost = new Image("img/bad.png");
	private static Image candy = new Image("img/candy-1.png");
	private static Image arrowRedUp = new Image("img/frhaut.png", 25, 25, false, false);
	private static Image arrowRedDown = new Image("img/frbas.png", 25, 25, false, false);
	private static Image arrowRedRight = new Image("img/frdroite.png", 25, 25, false, false);
	private static Image arrowRedLeft = new Image("img/frgauche.png", 25, 25, false, false);
	private static Image arrowBlueUp = new Image("img/fbhaut.png", 25, 25, false, false);
	private static Image arrowBlueDown = new Image("img/fbbas.png", 25, 25, false, false);
	private static Image arrowBlueRight = new Image("img/fbdroite.png", 25, 25, false, false);
	private static Image arrowBlueLeft = new Image("img/fbgauche.png", 25, 25, false, false);
	private static ImageView vPlayer = new ImageView(player);
	private static ImageView vGhost = new ImageView(ghost);
	private static ImageView vCandy = new ImageView(candy);
	private static ImageView vArrowUp = new ImageView(arrowRedUp);
	private static ImageView vArrowDown = new ImageView(arrowBlueDown);
	private static ImageView vArrowRight = new ImageView(arrowBlueRight);
	private static ImageView vArrowLeft = new ImageView(arrowBlueLeft);
	public static Scene scene;
	private static int score;
	private static int timer;
	private static Text scoreText;
	private static Text timerText;

	
	
	public static void drawFrame(Stage stage, int nbrX, int nbrY) {
		pane = new Group();
		scene = new Scene(pane, ((WALL + CELL) * nbrX + WALL) * SPAN + 4 * CELL * SPAN, ((WALL + CELL) * nbrY + WALL) * SPAN);
		scene.setFill(SCENE_COLOR);

		Rectangle square;
		stage.setScene(scene);

		square = new Rectangle(0, 0, SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);

		square = new Rectangle(0, SPAN * (nbrY * (CELL + WALL)), SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);

		square = new Rectangle(0, 0, WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);

		square = new Rectangle(SPAN * (nbrX * (CELL + WALL)), 0, WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);

		for (int x = 0; x < nbrX - 1; x++) {
			int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
			for (int y = 0; y < nbrY - 1; y++) {
				int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
				square = new Rectangle(offsetX, offsetY, WALL * SPAN, WALL * SPAN);
				square.setFill(WALL_COLOR);
				pane.getChildren().add(square);
			}
		}
		
		scoreText = new Text(scene.getWidth() - 3 * CELL * SPAN, 25,"");
		scoreText.setFill(Color.BLACK);
		pane.getChildren().add(scoreText);
		
		timerText = new Text(scene.getWidth() - 3 * CELL * SPAN, 75,"");
		timerText.setFill(Color.BLACK);
		pane.getChildren().add(timerText);
		
		drawArrowUp();
		drawArrowDown();
		drawArrowRight();
		drawArrowLeft();
		
		stage.show();
	}

	public static void drawWall(int xs, int ys, int xt, int yt, Paint color) {
		int x = 0, y = 0, xspan = 0, yspan = 0;
		if (ys == yt) {
			x = ((WALL + CELL) + (WALL + CELL) * ((int) (xs + xt) / 2)) * SPAN;
			y = (WALL + ys * (WALL + CELL)) * SPAN;
			xspan = WALL * SPAN;
			yspan = CELL * SPAN;
			Rectangle square = new Rectangle(x, y, xspan, yspan);
			square.setFill(color);
			pane.getChildren().add(square);
		} else if (xs == xt) {
			x = (WALL + xs * (WALL + CELL)) * SPAN;
			y = ((WALL + CELL) + (WALL + CELL) * ((int) (ys + yt) / 2)) * SPAN;
			xspan = CELL * SPAN;
			yspan = WALL * SPAN;
			Rectangle square = new Rectangle(x, y, xspan, yspan);
			square.setFill(color);
			pane.getChildren().add(square);
		}
	}

	public static void drawPlayer(int x, int y) {
		x = WALL * SPAN + x * (WALL + CELL) * SPAN;
		y = WALL * SPAN + y * (WALL + CELL) * SPAN;
		vPlayer.relocate(x, y);
		pane.getChildren().add(vPlayer);
	}

	public static void updatePlayer(int x, int y) {
		x = WALL * SPAN + x * (WALL + CELL) * SPAN;
		y = WALL * SPAN + y * (WALL + CELL) * SPAN;
		vPlayer.relocate(x, y);
	}

	public static void drawExit(int x, int y) {
		ImageView vDoor = new ImageView(door);
		x = WALL * SPAN + x * (WALL + CELL) * SPAN;
		y = WALL * SPAN + y * (WALL + CELL) * SPAN;
		vDoor.relocate(x, y);
		pane.getChildren().add(vDoor);
	}
	

	public static void drawGhost(int x, int y) {
		x = WALL * SPAN + x * (WALL + CELL) * SPAN;
		y = WALL * SPAN + y * (WALL + CELL) * SPAN;
		vGhost.relocate(x, y);
		pane.getChildren().add(vGhost);
	}

	public static void updateGhost(int x, int y) {
		x = WALL * SPAN + x * (WALL + CELL) * SPAN;
		y = WALL * SPAN + y * (WALL + CELL) * SPAN;
		vGhost.relocate(x, y);
	}
	
	public static void updateTime(int temps) {
		timerText.setText("Timer : "+ temps);
	}
	
	public static void updateScore(int score) {
		scoreText.setText("Score : "+ score);
	}

	public static void drawCandy(int x, int y) {
		x = WALL * SPAN + x * (WALL + CELL) * SPAN;
		y = 3 * WALL * SPAN + y * (WALL + CELL) * SPAN;
		vCandy.relocate(x, y);
		pane.getChildren().add(vCandy);
	}
	
	public static void deleteBonbon(int score) {
		scoreText.setText("Score : " + score);
		pane.getChildren().remove(vCandy);
	}
	
	public static void drawArrowUp() {
		vArrowUp.setImage(arrowRedUp);
		int x = (int) scene.getWidth() - 22 * SPAN;
		int y = 125;
		vArrowUp.relocate(x, y);
		pane.getChildren().add(vArrowUp);
	}
	
	public static void drawArrowDown() {
		vArrowDown.setImage(arrowBlueDown);
		int x = (int) scene.getWidth() - 22 * SPAN;
		int y = 175;
		vArrowDown.relocate(x, y);
		pane.getChildren().add(vArrowDown);
	}
	
	public static void drawArrowRight() {
		vArrowRight.setImage(arrowBlueRight);
		int x = (int) scene.getWidth() - 22 * SPAN + 25;
		int y = 150;
		vArrowRight.relocate(x, y);
		pane.getChildren().add(vArrowRight);
	}
	
	public static void drawArrowLeft() {
		vArrowLeft.setImage(arrowBlueLeft);
		int x = (int) scene.getWidth() - 22 * SPAN - 25;
		int y = 150;
		vArrowLeft.relocate(x, y);
		pane.getChildren().add(vArrowLeft);
	}
	
	public static void updateArrowUp() {
		vArrowUp.setImage(arrowRedUp);
		vArrowDown.setImage(arrowBlueDown);
		vArrowRight.setImage(arrowBlueRight);
		vArrowLeft.setImage(arrowBlueLeft);
	}
	
	public static void updateArrowDown() {
		vArrowUp.setImage(arrowBlueUp);
		vArrowDown.setImage(arrowRedDown);
		vArrowRight.setImage(arrowBlueRight);
		vArrowLeft.setImage(arrowBlueLeft);
	}
	
	public static void updateArrowRight() {
		vArrowUp.setImage(arrowBlueUp);
		vArrowDown.setImage(arrowBlueDown);
		vArrowRight.setImage(arrowRedRight);
		vArrowLeft.setImage(arrowBlueLeft);
	}
	
	public static void updateArrowLeft() {
		vArrowUp.setImage(arrowBlueUp);
		vArrowDown.setImage(arrowBlueDown);
		vArrowRight.setImage(arrowBlueRight);
		vArrowLeft.setImage(arrowRedLeft);
	}

}
