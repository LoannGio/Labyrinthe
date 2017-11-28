package model;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge implements Comparable<Edge>{
	
	private Type type;
	
	public enum Type{
		OPENED_DOOR,
		CLOSED_DOOR,
		CORRIDOR;
	};
	
	public Edge(Type type) {
		super();
		this.type = type;
	}

	//default
	
	public Edge() {
		super();
		this.type = Type.CORRIDOR;
	}
	
	public Vertex getSource() {
		return (Vertex) super.getSource();
	}
	
	public Vertex getTarget() {
		return (Vertex) super.getTarget();
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public void closeDoor(Edge edge) {
		edge.setType(Edge.Type.CLOSED_DOOR);
	}
	
	
	// FONCTIONS QUI SUIVENT A REPLACER ??
	
	public void closeDoorRandom() {
		Edge edge = graph.randomEdge();
		closeDoor(edge);
	}
	
	public boolean isWall(Vertex vertex, Directions dir) {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge == null);
	}
	
	public boolean isClosed(Vertex vertex, Directions dir) {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge == null || (edge.getType()==Edge.Type.CLOSED_DOOR)));
	}
	
	public boolean isOpened(Vertex vertex, Directions dir) {
		Edge edge = graph.getEdge(vertex, dir);
		return ((edge!=null)&&((edge.getType()!=Edge.Type.CLOSED_DOOR)));
	}
	
	public boolean isClosedDoor(Vertex vertex, Directions dir) {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge!=null && edge.getType()==Edge.Type.CLOSED_DOOR);
	}

	public boolean isOpenedDoor(Vertex vertex, Directions dir) {
		Edge edge = graph.getEdge(vertex, dir);
		return ((edge!=null) && ((edge.getType()==Edge.Type.OPENED_DOOR)));
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
