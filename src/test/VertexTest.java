
 package test;
  
 import static org.junit.Assert.*;
  
 import org.junit.Test;
 
 import model.Vertex; import model.direction;
  
 public class VertexTest {
 
	 @Test
	 public void testInBounds() {
		 Vertex v = new Vertex(0,3,2);
		 boolean res = v.inBounds();
		 if(res != true)
			 fail("Un sommet cense etre dans le labyrinthe n'y est pas");
	 
		 v = new Vertex(-1,0);
		 res = v.inBounds();
		 if(res != false)
			 fail("Un sommet cense etre hors du labyrinthe est dedans");
	 }
	 
	 @Test
	 public void testInBorders() {
		 Vertex v = new Vertex(1,1);
		 direction dir = direction.North;
		 boolean res = v.inBorders(dir);
		 if(res != true)
			 fail("Un sommet cense pouvoir se deplacer au nord ne peut pas");
	 
		 v = new Vertex(0,0); 
		 res = v.inBorders(dir);
		 if(res != false)
			 fail("Un sommet cense finir en dehors du labyrinthe peut y aller");
	 }
 
 }
 