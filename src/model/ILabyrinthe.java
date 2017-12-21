package model;


public interface ILabyrinthe {
	
	public void buildPath(Vertex v);
	public void openDoorRandom();
	public void calculateManhattanDistance(Vertex source, Vertex target) ;
	public void launchManhattan(Vertex source, Vertex target);
	public int checkCollision() ;

}
