package model;

public class Edge {
	
	private Vertex a;
	private Vertex b;
	
	
	public Edge(Vertex a, Vertex b) {
		super();
		this.a = a;
		this.b = b;
	}	
	
	public Vertex getA() {
		return a;
	}
	
	public void setA(Vertex a) {
		this.a = a;
	}
	
	public Vertex getB() {
		return b;
	}
	
	public void setB(Vertex b) {
		this.b = b;
	}
	
}
