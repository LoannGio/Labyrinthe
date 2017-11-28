package view;

import javafx.stage.Stage;

public class View {
	
	private static View instance = new View();
	
	private View() {
		
	}
	
	public static View getInstance() {
		return instance;
	}
	
	public void start(Stage primaryStage/*,JGraph graph*/) {
		//int nbSommets = graph.getNbSommets();
		int size = 3; //(int) Math.sqrt(nbSommets);
		ViewFrame.drawFrame(primaryStage, size, size);
		/*for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				V v1 = graph.getSommet(i, j);
				V v2;
				if((v2 = graph.getSommet(i-1, j)) != NULL && !graph.areAdjacent(v1, v2))
					ViewFrame.drawWall(i, j, i-1, j, ViewFrame.WALL_COLOR);
				else if((v2 = graph.getSommet(i+1, j)) != NULL && !graph.areAdjacent(v1, v2))
					ViewFrame.drawWall(i, j, i+1, j, ViewFrame.WALL_COLOR);
				else if((v2 = graph.getSommet(i, j-1)) != NULL && !graph.areAdjacent(v1, v2))
					ViewFrame.drawWall(i, j, i, j-1, ViewFrame.WALL_COLOR);
				else if((v2 = graph.getSommet(i, j+1)) != NULL && !graph.areAdjacent(v1, v2))
					ViewFrame.drawWall(i, j, i, j+1, ViewFrame.WALL_COLOR);
			}
		}*/
		ViewFrame.drawWall(1, 0, 1, 1, ViewFrame.WALL_COLOR);
		ViewFrame.drawWall(2, 0, 2, 1, ViewFrame.WALL_COLOR);
		ViewFrame.drawWall(0, 2, 1, 2, ViewFrame.WALL_COLOR);
		ViewFrame.drawWall(1, 1, 2, 1, ViewFrame.WALL_COLOR);
		ViewFrame.drawPlayer(2, 0);
		ViewFrame.drawDoor(0, 2);
		ViewFrame.drawCandy(0, 0);
		ViewFrame.drawVillain(2, 1);
	}
	
	public void updatePlayer(int x, int y) {
		ViewFrame.updatePlayer(x, y);
	}

}
