package model;

public abstract class Movable implements IMovable {
	private int _x;
	private int _y;
	private Boolean _isPackman;

	public void moveUp() {
		_y += 1;
	}

	public void moveDown() {
		_y -= 1;
	}

	public void moveLeft() {
		_x -= 1;
	}

	public void moveRight() {
		_x += 1;
	}
}
