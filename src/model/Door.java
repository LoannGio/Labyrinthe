package model;

public class Door extends Item{

	public Door() {
		setPosition(new Vertex(0, 0, 0));
	}

	public void startPosition() {
		int n1 = (int) (Math.random() * Labyrinthe.RIGHT_BORDER);
		int n2 = (int) (Math.random() * Labyrinthe.DOWN_BORDER);
		Vertex v = new Vertex(n1, n2);
		setPosition(v);
	}
}
