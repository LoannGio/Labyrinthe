package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Bonbon;
import model.Labyrinthe;
import model.Vertex;

public class BonbonTest {

	@Test
	public void testStartPosition() {
		Labyrinthe model = new Labyrinthe();
		model.getExit().startPosition();
		Vertex v = model.getExit().getPosition();
		model.getG().addVertex(v);
		model.buildPath(v);

				model.getPackman().startPosition(model, model.getG().getEqualVertex(v));
		model.getGhost().startPosition(model, model.getG().getEqualVertex(v));
		for(int j=0; j<4; j++) {
			model.getBonbons().get(j).startPosition(model, model.getPackman().getPosition());
		}
		for(int i = 0; i < 100000; i++) {
			for(Bonbon b : model.getBonbons()) {
				System.out.println(b.getPosition().getX() + ", " + b.getPosition().getY());
				if(b.getPosition().getX() == model.getExit().getPosition().getX() && b.getPosition().getY() == model.getExit().getPosition().getY())
					fail("Porte et bonbons sur la meme case à i = " + i);					
			}
		}
	}

}
