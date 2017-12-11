package view;

import javafx.stage.Stage;
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
		
		int longueur = laby.getRIGHT_BORDER()+1;
		int largeur = laby.getDOWN_BORDER()+1;
		ViewFrame.drawFrame(primaryStage, longueur, largeur);
		Graph graph = laby.getG();
		Packman player = laby.getPackman();
		Ghost ghost = laby.getGhost();
		Exit exit = laby.getExit();
		
		//Walls initialization
		for(int i = 0; i < longueur; i++) {
			for(int j = 0; j < largeur; j++) {
				ViewFrame.drawWall(i, j, i+1, j, ViewFrame.WALL_COLOR);
				ViewFrame.drawWall(i, j, i, j+1, ViewFrame.WALL_COLOR);
			}
		}
		
		
		for(int i = 0; i < longueur; i++) {
			for(int j = 0; j < largeur; j++) {
				Vertex v = graph.getEqualVertex(new Vertex(i, j));
				Vertex vUp = graph.getEqualVertex(new Vertex(i, j-1));
				Vertex vLeft = graph.getEqualVertex(new Vertex(i-1, j));
				Vertex vRight = graph.getEqualVertex(new Vertex(i+1, j));
				Vertex vDown = graph.getEqualVertex(new Vertex(i, j+1));
				
				if(graph.containsEdge(v, vUp) && vUp != null)
					ViewFrame.drawWall(v.getX(), v.getY(), vUp.getX(), vUp.getY(), ViewFrame.SCENE_COLOR);
				if(graph.containsEdge(v, vLeft) && vLeft != null)
					ViewFrame.drawWall(v.getX(), v.getY(), vLeft.getX(), vLeft.getY(), ViewFrame.SCENE_COLOR);
				if(graph.containsEdge(v, vRight) && vRight != null)
					ViewFrame.drawWall(v.getX(), v.getY(), vRight.getX(), vRight.getY(), ViewFrame.SCENE_COLOR);
				if(graph.containsEdge(v, vDown) && vDown != null)
					ViewFrame.drawWall(v.getX(), v.getY(), vDown.getX(), vDown.getY(), ViewFrame.SCENE_COLOR);
			}
		}
				
		ViewFrame.drawPlayer(player.getPosition().getX(), player.getPosition().getY());
		ViewFrame.drawExit(exit.getPosition().getX(), exit.getPosition().getY());
		ViewFrame.drawGhost(ghost.getPosition().getX(), ghost.getPosition().getY());
	}
	
	public void updatePlayer(int x, int y) {
		ViewFrame.updatePlayer(x, y);
	}

}
