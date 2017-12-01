package model;

import model.Labyrinthe.direction;

public abstract class Movable extends Item {

	protected abstract void startPosition(Labyrinthe labyrinthe, Vertex v);

	public void move(Labyrinthe labyrinthe, direction dir) {
		Vertex vertex = this.getRealPosition(labyrinthe.getG());
		Vertex next = labyrinthe.getG().getEqualVertex(labyrinthe.getG().vertexByDir(vertex, dir));
		if (labyrinthe.getG().containsEdge(vertex, next) && next != null) {
			this.setPosition(next);
		}
	}

}
