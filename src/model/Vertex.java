package model;

import model.Labyrinthe.direction;

public class Vertex {
	
	private int x;
	private int y;
	private int nbr;
	
	public Vertex(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.nbr = 0;
	}
	
	public Vertex(int x, int y, int nbr) {
		super();
		this.x = x;
		this.y = y;
		this.nbr = nbr;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getNbr() {
		return nbr;
	}

	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

	public String toString() {
		return "x="+this.x+" y="+this.y;
	}
	
	public boolean inBounds() {
		if ((this.getX() < Labyrinthe.LEFT_BORDER) 
		|| (this.getX() > Labyrinthe.RIGHT_BORDER) 
		|| (this.getY() < Labyrinthe.TOP_BORDER) 
		|| (this.getY() > Labyrinthe.DOWN_BORDER)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean inBorders(direction dir) {
		Vertex v = new Vertex(this.getX(),this.getY());
		switch(dir) {
		case North :
			v.setY(v.getY()-1);
			break;
		case South :
			v.setY(v.getY()+1);
			break;
		case East :
			v.setX(v.getX()+1);
			break;
		case West :
			v.setX(v.getX()-1);
			break;
		}
		return v.inBounds();
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((x == ((Vertex) obj).x) && (y == ((Vertex) obj).y));
	}

}
