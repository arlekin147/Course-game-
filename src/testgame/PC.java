package testgame;

import org.lwjgl.*;


import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;


import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.*;


import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;



public class PC extends Character{
	
	public static int RANGE = 100;
	private long window;
	private int power = 1;
	private int currentItem;
	private Inventory inventory = new Inventory(50);
	
	public PC(World observer ,long window, Animation pic, Coordinates ne,  Coordinates sw){
		super(observer, pic , ne , sw);
		this.inventory.add(observer.iF.getItem(1000));
		this.window = window;
		this.isLookingRight = true;
	}
	
	public PC(World observer,long window ,Animation pic, int ulx,double f,double d, double e){
	    	
	       this(observer, window, pic, new Coordinates(ulx,f), new Coordinates(d,e));
	}
	
	public void control(World world) {
		this.inventory.update();
		this.isMove = false;
		this.derection = -1;
		
		if(((glfwGetMouseButton(window, 0) == 1) || (glfwGetMouseButton(window, 1) == 1)))
		{
			Coordinates coordOfMouse = this.getCoordOfMouse(true);
			if(glfwGetMouseButton(window, 0) == 1) {
				if(this.inventory.getActiveItem() != null)
					this.inventory.getActiveItem().LMB(this.ul.x, this.ul.y, coordOfMouse.x, coordOfMouse.y);
				else this.destroy(coordOfMouse);
			}
			if(glfwGetMouseButton(window, 1) == 1) {
				if(this.inventory.getActiveItem() != null)
					this.inventory.getActiveItem().RMB(this.ul.x, this.ul.y, coordOfMouse.x, coordOfMouse.y);
			}
			
		}
		
		
		if((glfwGetKey(window,GLFW_KEY_0)) == 1) {
			this.inventory.setActive(9);
		}
		if((glfwGetKey(window,GLFW_KEY_1)) == 1) {
			this.inventory.setActive(0);
		}
		if((glfwGetKey(window,GLFW_KEY_2)) == 1) {
			this.inventory.setActive(1);
		}
		if((glfwGetKey(window,GLFW_KEY_3)) == 1) {
			this.inventory.setActive(2);
		}
		if((glfwGetKey(window,GLFW_KEY_4)) == 1) {
			this.inventory.setActive(3);
		}
		if((glfwGetKey(window,GLFW_KEY_5)) == 1) {
			this.inventory.setActive(4);
		}
		if((glfwGetKey(window,GLFW_KEY_6)) == 1) {
			this.inventory.setActive(5);
		}
		if((glfwGetKey(window,GLFW_KEY_7)) == 1) {
			this.inventory.setActive(6);
		}
		if((glfwGetKey(window,GLFW_KEY_8)) == 1) {
			this.inventory.setActive(7);
		}
		if((glfwGetKey(window,GLFW_KEY_9)) == 1) {
			this.inventory.setActive(8);
		}
		
		Coordinates crdnt = this.getCoordOfMouse(false);
		
		int i = 0;
		for (Coordinates ul = new Coordinates (1,1); ul.x <= 36-3.5; ul.x+=3.5,i++)
		{
			Coordinates br = new Coordinates(ul.x + 3.5,ul.y+3);
			
			if (World.isAinB(crdnt, ul,br) && glfwGetMouseButton(window, 0) == 1 ) 
			{
				this.inventory.setActive(i);
			}
		}
		
		
		GLFW.glfwSetScrollCallback(window, new GLFWScrollCallback() {
		    @Override public void invoke (long win, double dx, double dy) {
		       if (dy!=0) inventory.getNext((int)dy);
		        
		    }
		});

		
		int animationMode = 0; //TODO ENUM
		
		
		
		if((glfwGetKey(window,GLFW_KEY_D)) == 1 )
		{
				this.vx = 0.1;
				derection = 1;		
				this.isMove = true;
				this.isLookingRight = true;
			
		}else if((glfwGetKey(window,GLFW_KEY_A))==1){
			this.vx = -0.1;
			animationMode = 2;
			this.isLookingRight = false;
			this.derection = 0;
			this.isMove = true;
		}
		else {
			animationMode = (this.isLookingRight)?1:3;
			this.vx = 0;
		}
		if((glfwGetKey(window,GLFW_KEY_SPACE))==1) {
			
			{
				this.isJumpNeeded = true;		
			}
			
		}
		this.setAnimation(animationMode);
		
	}
	
	public void giveItem(Item item) {
		this.inventory.add(item);
	}
	
	private void destroy (Coordinates coordOfMouse) {

		if(this.isInRange(coordOfMouse))
			this.observer.destroy(coordOfMouse, power);
	}
	
	private void makeBlock(Block block) {
		Coordinates coordOfMouse = block.coord;
		if(this.isInRange(coordOfMouse))
			this.observer.spawn(block);
			
	}
	
	private void makeDamage(Coordinates coordOfMouse) {
		//this.observer.makeDamage():
	}
	
	
	private boolean isInRange(Coordinates coordOfMouse) {
		return (Math.sqrt((this.ul.x - coordOfMouse.x) * (this.ul.x - coordOfMouse.x) +
				((this.ul.y - coordOfMouse.y)*(this.ul.y - coordOfMouse.y))) <= PC.RANGE);
	}
	
	private Coordinates getCoordOfMouse(boolean isCameraRelated) {
		DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
		
		double partX = isCameraRelated? this.observer.cam.getX() : 0;
		double partY = isCameraRelated? this.observer.cam.getY() : 0;
		
		glfwGetCursorPos(window, b1, b2);
		double xOfMouse = ((b1.get(0) / World.blockSize) + partX);
		double yOfMouse = ((b2.get(0) / World.blockSize) + partY);
		
		return new Coordinates(xOfMouse, yOfMouse);
	}
		
	
	public void draw() {
		inventory.draw();
		if (currentAnimation == 0)
    	{
    		this.pic.bind();
    	}
    	else 
    	{
    		this.animations[currentAnimation].bind();
    	}
    	
		
		//WJIEFJIWEF
    	
        glBegin(GL_QUADS); 
       
    	//glTexCoord2d(0,0.5);
    	glTexCoord2d(0,0);
        glVertex2d(TestGame.WIDTH/2/World.blockSize,(TestGame.HEIGHT/2  - 52)/World.blockSize); //upper left
       
       /// glTexCoord2d(0,0.75);
        glTexCoord2d(0,1);
        glVertex2d(TestGame.WIDTH/2/World.blockSize,TestGame.HEIGHT/2/World.blockSize); //bottom left 
       
        //glTexCoord2d(0.33,0.75);
         glTexCoord2d(1,1);
        glVertex2d((TestGame.WIDTH/2/World.blockSize + 1.6),TestGame.HEIGHT/2/World.blockSize); //bottom right 
       
        
     // glTexCoord2d(0.33,0.5);
        glTexCoord2d(1,0);
        glVertex2d((TestGame.WIDTH/2/World.blockSize + 1.6),(TestGame.HEIGHT/2  - 52)/World.blockSize); //upper right
       
        glEnd();
        
			
	}
}
