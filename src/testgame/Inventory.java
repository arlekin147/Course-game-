package testgame;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.util.ArrayList;

public class Inventory {
	 private int activeItem;
     private Item[] items;
     private int firstFree;
     private Texture[] textures;

     public Inventory(int size)
     {
    	 textures = new Texture[size];
    	 for(int i = 0; i < 10; ++i) {
    		 this.textures[i] = new Texture("res/inventory_1" + (i+1) + "_active.png");
    	 }
         items = new Item[size];
         firstFree=0;
     }
     
     public Item getActiveItem() {
    	 return this.items[activeItem];
     }
     
     public void getNext(double a)
     {
    	int dir = (a>0)?1:-1;
    	activeItem = (activeItem + dir)%items.length;
    	if (activeItem < 0) activeItem = 9;
    		 
     }
     
     public void update() {
    	 for(Item item : this.items) {
    		 if(item instanceof Stackable) {
    			 Stackable s = (BlockItem)item;
    			 if(s.isEmpty()) {
    				 this.remove(item);
    			 }
    		 }
    	 }
     }

     public boolean add(Item item)
     {
    	 boolean added = false;
    	 
    	 for(Item it : this.items) {
    		 if(it != null && it.itemId == item.itemId && it instanceof Stackable) {
    			 Stackable s = (BlockItem)it; //..... fuck
    			 added = s.stack();
    			 System.out.println(added);
    		 }
    	 }
    	 if(!added) {
	    	 
	         if(firstFree == items.length)
	             return false;
	         items[firstFree] = item;
	         
	      
	         boolean isFull = true;
	         for(int i = firstFree + 1; i < items.length; ++i)
	         {
	        	 
	             if(items[i] == null)
	             {	
	            	 isFull = false;
	                 firstFree = i;
	                 break;
	             }
	         }
	         if (isFull) firstFree = items.length;
    	 }
       return true;
     }
     
     public int find() {
    	 return -1;
     }

     public Item getItem(int index)
     {
    	 if(index < this.items.length) return items[index];
    	 else return null;
     }
     public void remove(int index)
     {
    	 if(index < this.items.length)
    		 items[index]=null;
    	  if(index<firstFree)
             firstFree=index;
     }
     public void remove(Item item)
     {
         for (int i = 0; i < items.length; i++)
             if(items[i ]== item)
             {
                 items[i] = null;
                 if (i < firstFree)
                     firstFree = i;
                 break;
             }
     }

	public void draw() {
		this.textures[this.activeItem].bind();
		 glBegin(GL_QUADS); 
	       
	    	//glTexCoord2d(0,0.5);
	    	glTexCoord2d(0,0);
	        glVertex2d(1,1); //upper left
	       
	       /// glTexCoord2d(0,0.75);
	        glTexCoord2d(0,1);
	        glVertex2d(1,4); //bottom left 
	       
	        //glTexCoord2d(0.33,0.75);
	         glTexCoord2d(1,1);
	        glVertex2d(36,4); //bottom right 
	       
	        
	     // glTexCoord2d(0.33,0.5);
	        glTexCoord2d(1,0);
	        glVertex2d(36,1); //upper right
	       
	    glEnd();
	    for(int i = 0; i < this.items.length; ++i) {
	    	if(this.items[i] != null)
	    		this.items[i].draw(new Coordinates(3.5*i + 2, 2), new Coordinates(3.5*i + 3, 3));
	   }
	        
		// i + 1,5, i + 1,5
	}

	public void setActive(int i) {
		this.activeItem = i;
	}
}
