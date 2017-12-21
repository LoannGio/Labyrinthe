package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Bonbon;
import model.Labyrinthe;
import model.Vertex;

public class LabyrintheTest {

	@Test
	public void testCheckCollision() {
		Labyrinthe laby = new Labyrinthe();
		laby.getPackman().setPosition(new Vertex(1,1));
		laby.getGhost().setPosition(new Vertex(1,1));
		laby.getExit().setPosition(new Vertex(2,1));
		//laby.getBonbon().setPosition(new Vertex(2,2));
		for(Bonbon b : laby.getBonbons()) {
			b.setPosition(new Vertex(b.getType()+10, b.getType()+10));
		}

		int res = laby.checkCollision();
		if(res != 0)
			fail("Collision joueur - mechant");
		
		laby.getGhost().setPosition(new Vertex(2,1));
		laby.getExit().setPosition(new Vertex(1,1));
		res = laby.checkCollision();
		if(res != 1)
			fail("Collision joueur - sortie");
		
		laby.getExit().setPosition(new Vertex(2,2));
		laby.getBonbons().get(0).setPosition(new Vertex(1,1));
		res = laby.checkCollision();
		if(res != 2)
			fail("Collision joueur - bonbon 1");		
		laby.getBonbons().get(0).setPosition(new Vertex(11,11));
	
		laby.getBonbons().get(1).setPosition(new Vertex(1,1));
		res = laby.checkCollision();
		if(res != 3)
			fail("Collision joueur - bonbon 2");		
		laby.getBonbons().get(1).setPosition(new Vertex(12,12));
		
		laby.getBonbons().get(2).setPosition(new Vertex(1,1));
		res = laby.checkCollision();
		if(res != 4)
			fail("Collision joueur - bonbon 3");		
		laby.getBonbons().get(2).setPosition(new Vertex(13,13));
		
		laby.getBonbons().get(3).setPosition(new Vertex(1,1));
		res = laby.checkCollision();
		if(res != 5)
			fail("Collision joueur - bonbon 4");		
		laby.getBonbons().get(3).setPosition(new Vertex(14,14));
		
		
		res = laby.checkCollision();
		if(res != -1)
			fail("Collision joueur - pas de collision");
	}

}
