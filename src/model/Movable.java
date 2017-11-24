package model;

public abstract class Movable implements IMovable {
	protected int _x;
	protected int _y;
	protected Boolean _isPackman;

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
