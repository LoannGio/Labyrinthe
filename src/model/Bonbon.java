package model;

public class Bonbon extends Item {
	
	static final int minDist = 11;
	
	public Bonbon() {
		setPosition(new Vertex(0, 0, 0));
	}


	public void startPosition(Labyrinthe labyrinthe, Vertex niceGuyPosition) {
		Vertex v;
		do {
			v = labyrinthe.getG().getEqualVertex(labyrinthe.getG().randomVertex());
			labyrinthe.launchManhattan(v, niceGuyPosition);
		} while ((v.getNbr() < minDist) && (!v.equals(labyrinthe.getExit().position)));
		this.setPosition(v);
	}
}

