package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
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
	private static Image villain = new Image("img/bad.png");
	private static Image candy = new Image("img/candy-1.png");
	private static ImageView vPlayer = new ImageView(player);
	private static ImageView vVillain = new ImageView(villain);
	public static Scene scene;

	public static void drawFrame(Stage stage, int nbrX, int nbrY) {
		pane = new Group();
		scene = new Scene(pane, ((WALL + CELL) * nbrX + WALL) * SPAN, ((WALL + CELL) * nbrY + WALL) * SPAN);
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

	public static void drawVillain(int x, int y) {
		x = WALL * SPAN + x * (WALL + CELL) * SPAN;
		y = WALL * SPAN + y * (WALL + CELL) * SPAN;
		vVillain.relocate(x, y);
		pane.getChildren().add(vVillain);
	}

	public static void updateVillain(int x, int y) {
		x = WALL * SPAN + x * (WALL + CELL) * SPAN;
		y = WALL * SPAN + y * (WALL + CELL) * SPAN;
		vVillain.relocate(x, y);
	}

	public static void drawCandy(int x, int y) {
		ImageView vCandy = new ImageView(candy);
		x = WALL * SPAN + x * (WALL + CELL) * SPAN;
		y = 3 * WALL * SPAN + y * (WALL + CELL) * SPAN;
		vCandy.relocate(x, y);
		pane.getChildren().add(vCandy);
	}
}
