package model;

public abstract class Item {

	protected Vertex position;

	public Vertex getPosition() {
		return position;
	}

	public Vertex getRealPosition(Graph graph) {
		return graph.getEqualVertex(position);
	}

	public void setPosition(Vertex position) {
		this.position = position;
	}

}