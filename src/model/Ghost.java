package model;

public class Ghost extends Movable {

	static final int minDist = 10;
	
	
	
	
	public void move(Labyrinthe labyrinthe) {
		if(!(labyrinthe.getPackman().getPosition().getX() == labyrinthe.getGhost().getPosition().getX() && labyrinthe.getPackman().getPosition().getY() == labyrinthe.getGhost().getPosition().getY())) {
			Vertex vertex = this.getRealPosition(labyrinthe.getG());
			int min = 1000; 
			direction direc = null;
			for (direction dir : direction.values()) {
				Vertex next = labyrinthe.getG().getEqualVertex(labyrinthe.getG().vertexByDir(vertex, dir));
				if (labyrinthe.getG().containsEdge(vertex, next) && (next.getNbr() == vertex.getNbr() - 1)
					&& next != null){
					        labyrinthe.launchManhattan(vertex, labyrinthe.getPackman().position);
					        if(vertex.getNbr()<min) {
					        	min = vertex.getNbr();
					        	direc = dir;
					        }
				}
			}
			if(direc != null)
				this.move(labyrinthe, direc);
		}
		
	}

	


	@Override
	public void startPosition(Labyrinthe labyrinthe, Vertex niceGuyPosition) {
		Vertex v;
		do {
			v = labyrinthe.getG().getEqualVertex(labyrinthe.getG().randomVertex());
			labyrinthe.launchManhattan(v, niceGuyPosition);
		} while (v.getNbr() < minDist);
		this.setPosition(v);
	}

}
