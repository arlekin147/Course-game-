package testgame;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

public abstract class Item extends Entity {
	
	protected int itemId;
	
	public Item(World observer,Animation pic, double ulx, double uly, double brx, double bry, int itemId) {
		super(observer,pic, ulx, uly, brx, bry);
		this.itemId = itemId;
		// TODO Auto-generated constructor stub
	}
	
	public Item() { //Заглушка
		
	}
	
	public void draw(Coordinates ul, Coordinates br) {
		this.pic.bind();
		 glBegin(GL_QUADS); 
	       
	    	//glTexCoord2d(0,0.5);
	    	glTexCoord2d(0,0);
	        glVertex2d(ul.x,ul.y); //upper left
	       
	       /// glTexCoord2d(0,0.75);
	        glTexCoord2d(0,1);
	        glVertex2d(ul.x,br.y); //bottom left 
	       
	        //glTexCoord2d(0.33,0.75);
	         glTexCoord2d(1,1);
	        glVertex2d(br.x, br.y); //bottom right 
	       
	        
	     // glTexCoord2d(0.33,0.5);
	        glTexCoord2d(1,0);
	        glVertex2d(br.x,ul.y); //upper right
	       
	    glEnd();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public abstract void LMB(double userX , double userY , double x , double y);
	
	public abstract void RMB(double userX , double userY , double x , double y);
	
	
}
