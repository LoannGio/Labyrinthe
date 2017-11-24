package model;

public abstract class Movable implements IMovable {
	private int x;
	private int y;
	private Boolean isPackman;

	public void moveUp() {
		y += 1;
	}

	public void moveDown() {
		y -= 1;
	}

	public void moveLeft() {
		x -= 1;
	}

	public void moveRight() {
		x += 1;
	}
}
