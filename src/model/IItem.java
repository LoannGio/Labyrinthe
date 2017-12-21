package model;

public interface IItem {

	public Vertex getPosition();
	public Vertex getRealPosition(Graph graph);
	public void setPosition(Vertex position);

}
