package model;


import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import model.Labyrinthe.direction;

public class Graph {

	private SimpleGraph<Vertex, Edge> g;

	public Graph() {
		super();
		this.g = new SimpleGraph<Vertex, Edge>(Edge.class);
	}
	
	public SimpleGraph<Vertex, Edge> getG() {
		return g;
	}
		
	public void addVertex(Vertex v) {
		this.g.addVertex(v);
	}
	
	public boolean containsVertex(Vertex v) {
		return this.g.containsVertex(v);
	}
	
	public Object[] vertexSet() {
		return this.g.vertexSet().toArray();
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		Edge e = new Edge(v1, v2);
		this.g.addEdge(v1, v2, e);
	}
	
	public boolean containsEdge(Vertex v1, Vertex v2) {
		return this.g.containsEdge(v1, v2);
	}
	
	public String toString() {
		return this.g.toString();
	}
	
	public Vertex randomVertex() {
		int n1 = (int) (Math.random() * Labyrinthe.RIGHT_BORDER);
		int n2 = (int) (Math.random() * Labyrinthe.DOWN_BORDER);
		Vertex v = new Vertex(n1, n2);
		return v;
	}
	
	public boolean vertexInGraph(Vertex v) {
		Object[] tab = g.vertexSet().toArray();
		for (Object v2 : tab) {
			if (v.equals((Vertex) v2)) {
				return true;
			}
		}
		return false;
	}
	
	public Vertex getEqualVertex(Vertex v) {
		if (v == null) {
			return null;
		}
		Object[] tab = g.vertexSet().toArray();
		for (Object v2 : tab) {
			if (v.equals((Vertex) v2)) {
				return (Vertex) v2;
			}
		}
		return null;
	}
	
	public Vertex vertexByDir(Vertex v, direction dir) {
		Vertex v2 = new Vertex(v.getX(), v.getY());
		switch(dir) {
		case North :
			v2.setY(v.getY()-1);
			break;
		case South :
			v2.setY(v.getY()+1);
			break;
		case East :
			v2.setX(v.getX()+1);
			break;
		case West :
			v2.setX(v.getX()-1);
			break;
		}
		if (v2.inBounds()) {
			return v2;
		}
		else {
			return null;
		}
	}
	
	public boolean vertexDoesntExist(Vertex v, direction dir) {
		Vertex v2 = vertexByDir(v, dir);
		return !this.vertexInGraph(v2);
	}
	
	public boolean edgeDoesntExist(Vertex v, direction dir) {
		Vertex v2 = getEqualVertex(vertexByDir(v, dir));
		return !this.containsEdge(v, v2);
	}
	
	public void generateRandomGraph(int nbSommet, int nbArrete) {
		
		SimpleGraph<Vertex, DefaultEdge> g = new SimpleGraph<Vertex, DefaultEdge>(DefaultEdge.class);
		int x;
		int y;
		
		for (int i = 0 ; i < nbSommet ; i++) {
			x = (int) (Math.random() * nbSommet);
			y = (int) (Math.random() * nbSommet);
			Vertex v = new Vertex(x, y);
			g.addVertex(v);
		}
		
		
		Object[] vertices = g.vertexSet().toArray();
		
		for (int l = 0 ; l < nbArrete ; l++) {
			int n1 = (int) (Math.random() * nbSommet);
			int n2 = (int) (Math.random() * nbSommet);
			while (n1 == n2) {
				n2 = (int) (Math.random() * nbSommet);
			}
			
			Vertex v1 = (Vertex) vertices[n1];
			Vertex v2 = (Vertex) vertices[n2];
			if (!g.containsEdge(v1, v2)) {
				g.addEdge(v1, v2);
			}
		}
		System.out.println(g.toString());
	}
	
	
	public Graph generateStableGraph(int dim) {
		Graph g = new Graph();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				Vertex v = new Vertex(i, j);
				this.g.addVertex(v);
			}
		}
		return g;
	}
	
}
