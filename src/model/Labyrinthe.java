package model;

import org.jgrapht.Graph;

import java.util.Queue;
import java.util.Vector<E>;

public class Labyrinthe {

	public void buildRandomPath(Vertex vertex) {
		//une liste aleatoire des 4 directions
		Vector<Directions> v = new Vector<Directions>();
		
		for(int i = 0; i < 4; ++i)
			v.add(Directions.values()[i]);
		Directions directions[] = new Directions[4];
		for(int i = 0; i < directions.length; ++i) {
			int index = random.nextInt(v.size());
			directions[i] = v.get(index);
			v.remove(index);
		}
		
		//pour chacune de ces directions, on avance en profondeur d abord
		
		for(int i=0; i < 4; ++i) {
			Directions dir = directions[i];
			if (vertex.inBorders(dir) && Graph.doesntExist(vertex.dir)) {
				int x = vertex.getX();
				int y = vertex.getY();
				int xt = 0, yt = 0;
				switch(dir) {
					case NORTH: xt = x; yt = y-1; break;
					case SOUTH: xt = x; yt = y+1; break;
					case EAST: xt = x+1; yt = y; break;
					case WEST: xt = x-1; yt = y; break;
				}
				Vertex next = new Vertex(xt, yt, vertex.getNbr()+1);
				graph.addVertex(next);
				graph.addEdge(vertex, next);
				buildRandomPath(next);
			}
		}			
	}
	
	public void openDoorRandom() {
		// On essaie 1000 fois, apres quoi on renonce
		for(int i=1; i<=1000;++i) {
			//On choisi un sommet au hasard
			Vertex vertex = graph.randomVertex();
			if(vertex!=null) {
				//On choisi une direction au hasard (on devrait prendre seulement
				// celles qui correspondent a des murs...)
				
				Labyrinth.Directions dir = Directions.values()[random.nextInt(Directions.values().length)];
				if(isWall(Vertex,dir)) {
					Vertex vertex2 = graph.getVertexByDir(vertex, dir);
					if(vertex2!=null) {
						// On rajoute un saut entre ces sommets
						graph.addEdge(vertex,vertex2);
							new Edge(Type.OPENED_DOOR));
						return;
					}
				}
			}
		}
	}
	
	private void calculateManhattanDistance(Vertex source, Vertex target) {
		Queue<Vertex> fifo = new ArrayDeque<Vertex> ();
		target.setNbr(1);
		fifo.add(target);
		while(!fifo.isEmpty()) {
			Vertex actual = fifo.remove();
			for(Directions dir : Directions.values()) {
				if(this.isOpened(actual, dir)) {
					Vertex next = graph.getVertexByDir(actual,dir);
					if(next.getNbr()==0) {
						next.setNbr(actual.getNbr()+1);
						if(next!=source)
							fifo.add(next);
					}
				}
			}
		}
	}
	
	public void launchManhattan(Vertex source, Vertex target) {
		for(Vertex vertex : graph.vertexSet()) {
			vertex.setNbr(0);
		}
		calculateManhattanDistance(source,target);
	}

}
