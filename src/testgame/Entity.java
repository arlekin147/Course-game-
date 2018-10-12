/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2i;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

/**
 *
 * @author РђР»РµРєСЃР°РЅРґСЂ
 */
public abstract class Entity implements IObservable{

	protected World observer;
    protected Coordinates ul; //upper-left
    protected Coordinates br; //bottom-right
    protected double vx; 
	protected double vy;
    protected Animation pic; 
    protected Animation[] animations = new Animation[4]; 
    protected int currentAnimation = 0;
    protected boolean isMove;
	protected boolean isJumping;
	protected boolean isLookingRight;
	protected boolean isJumpNeeded;
    protected int derection = -1; //TODO ENUM 0 - лево 1 - право 2 - верх 3 -низ    
    
    public Entity() {//Времменно
    	
    }
    public void move(double x,double y) 
    {
    	this.ul.x += x;
    	this.ul.y += y;
    	this.br.x += x;
    	this.br.y += y;
    }
    
    public void setDirection(int d)
    {
    	this.derection = d;
    }
    public void move()
    {
    	//System.out.println(x + " " + y);
    	this.ul.x += vx;
    	this.ul.y += vy;
    	this.br.x += vx;
    	this.br.y += vy;
    	
    	//System.out.println("(" + ul.x + "," + ul.y + ") (" + br.x + "," + br.y + ") ");	    	
    }
	public final Coordinates getUr() {   //upper-right
		return new Coordinates(br.x,ul.y);
	}
	
	public int getDerection() {
		return this.derection;
	}
	
	public void setVX(double vx) {
		this.vx = vx;
	}
	
	public void setVY(double vy) {
		this.vy = vy;
	}
	
	public double getVX() {
		return vx;
	}
	
	public double getVY() {
		return vy;
	}
	
	public boolean isMove() {
		return this.isMove;
	}
	
	public final Coordinates getBl() {  //bottom-left
		return new Coordinates(ul.x,br.y);
	}
	
	public void setAnimation(int i)
	{
		this.currentAnimation = i;
	}

	
    public Entity(World observer ,Animation pic, Coordinates ne,  Coordinates sw){
    	this.pic = pic;
    	this.ul = ne;
    	this.br = sw;
    	this.observer = observer;
    	this.animations[1] = new Animation(1,1,"res/0");
    	this.animations[2] = new Animation(3,9,"res/3");
    	this.animations[3] = new Animation(1,1,"res/3"); //TODO
    }
    
    public Entity(World observer ,Animation pic, double ulx,double uly,double brx, double bry){
    
       this(observer ,pic,new Coordinates(ulx,uly),new Coordinates(brx,bry));
    }
    
    
    public void draw(Camera cam){
    	if (currentAnimation == 0)
    	{
    		this.pic.bind();
    	}
    	else 
    	{
    		this.animations[0].bind();
    	}
    	
    	
        glBegin(GL_QUADS); 
       
    	//glTexCoord2d(0,0.5);
    	/*glTexCoord2d(0,0);
        glVertex2d(ul.x,ul.y); //upper left
       
       /// glTexCoord2d(0,0.75);
        glTexCoord2d(0,1);
        glVertex2d(ul.x,br.y); //bottom left 
       
        //glTexCoord2d(0.33,0.75);
         glTexCoord2d(1,1);
        glVertex2d(br.x,br.y); //bottom right 
       
        
     // glTexCoord2d(0.33,0.5);
        glTexCoord2d(1,0);
        glVertex2d(br.x,ul.y); *///upper right
        
        glTexCoord2i(0,0);
        glVertex2d(ul.x -cam.getX(),ul.y -cam.getY());
        glTexCoord2i(1,0);
        glVertex2d(br.x -cam.getX(), ul.y -cam.getY());
        glTexCoord2i(1,1);
        glVertex2d(br.x -cam.getX(),br.y-cam.getY());
        glTexCoord2i(0,1);
        glVertex2d(ul.x -cam.getX(),br.y-cam.getY());
       
        glEnd();
      }
    
      public Coordinates getCoord(){
       	return new Coordinates(ul.x,ul.y);
      }
}



