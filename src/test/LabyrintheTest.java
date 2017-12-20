package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Labyrinthe;
import model.Vertex;

public class LabyrintheTest {

	@Test
	public void testCheckCollision() {
		Labyrinthe laby = new Labyrinthe();
		laby.getPackman().setPosition(new Vertex(1,1));
		laby.getGhost().setPosition(new Vertex(1,1));
		laby.getExit().setPosition(new Vertex(2,1));
		laby.getBonbon().setPosition(new Vertex(2,2));
		int res = laby.checkCollision();
		if(res != 0)
			fail("Collision joueur - méchant");
		
		laby.getGhost().setPosition(new Vertex(2,1));
		laby.getExit().setPosition(new Vertex(1,1));
		res = laby.checkCollision();
		if(res != 1)
			fail("Collision joueur - méchant");
		
		laby.getExit().setPosition(new Vertex(2,2));
		laby.getBonbon().setPosition(new Vertex(1,1));
		res = laby.checkCollision();
		if(res != 2)
			fail("Collision joueur - méchant");
		
		res = laby.checkCollision();
		if(res != -1)
			fail("Collision joueur - méchant");
	}

}
