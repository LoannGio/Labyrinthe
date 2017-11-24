package model;

public class Packman extends Movable {
	private static Packman instance = null;

	private Packman(int x, int y) {
		_x = x;
		_y = y;
		_isPackman = true;
	}

	public static Packman createInstance(int x, int y) {
		if (instance == null) {
			instance = new Packman(x, y);
		}
		return instance;
	}

	public static Packman getInstance() {
		return instance;
	}
}