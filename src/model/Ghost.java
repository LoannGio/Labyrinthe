package model;

public class Ghost extends Movable {

	static final int minDist = 10;
	
	public void move(Labyrinthe labyrinthe) {
		Vertex vertex = this.getRealPosition(labyrinthe.getG());
		for (direction dir : direction.values()) {
			Vertex next = labyrinthe.getG().vertexByDir(vertex, dir);
			if (labyrinthe.getG().containsEdge(vertex, next) && (next.getNbr() == vertex.getNbr() - 1)
					&& next != null) {
				this.move(labyrinthe, dir);
			}
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
