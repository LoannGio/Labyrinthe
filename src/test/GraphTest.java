package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Edge;
import model.Graph;
import model.Vertex;

public class GraphTest {

	@Test
	public void testContainsVertex() {
		Graph g = new Graph();
		Vertex v = new Vertex(1,1);
		
		if(g.containsVertex(v))
			fail("Un Graph contient un Vertex qu'il ne devrait pas");
	
		g.addVertex(v);
		if(!(g.containsVertex(v)))
			fail("Un Graph ne contient pas un Vertex qu'il devrait avoir");
	}
	
	@Test
	public void testContainsEdge() {
		Graph g = new Graph();
		Vertex v1 = new Vertex(1,1);
		Vertex v2 = new Vertex(1,2);
		
		if(g.containsEdge(v1, v2))
			fail("Un Graph contient un Edge qu'il ne devrait pas");
		
		g.addVertex(v1);
		g.addVertex(v2);
		g.addEdge(v1, v2);
		if(!(g.containsEdge(v1, v2)))
			fail("Un Graph ne contient pas un Edge qu'il devrait avoir");
	}

}
