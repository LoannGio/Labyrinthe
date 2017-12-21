package model;

public class Bonbon extends Item {
	
	private int type;
	private int minDist = 11;
	
	public Bonbon(int type) {
		setPosition(new Vertex(0, 0, 0));
		this.type = type;
	}

	public int getType() {
		return type; 
	}
	public void startPosition(Labyrinthe labyrinthe, Vertex niceGuyPosition) {
		Vertex v;
		do {
			v = labyrinthe.getG().getEqualVertex(labyrinthe.getG().randomVertex());
			labyrinthe.launchManhattan(v, niceGuyPosition);
		} while ((v.getNbr() < minDist+2*type) && (!v.equals(labyrinthe.getExit().position)));
		this.setPosition(v);
	}
}

