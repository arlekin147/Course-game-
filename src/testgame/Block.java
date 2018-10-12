

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import java.util.*;

/**
 *
 * @author Александр
 */
public class Block{
	public static ArrayList<ArrayList<Texture>> textures = new ArrayList<ArrayList<Texture>>();

	protected World observer;
	protected Coordinates coord;
	protected int currentTexture = 0;
	protected int strength = 5;
    /*public Block() {
        this.blockId = 0;
    }*/
	public Block() {
		this.blockId = -1; //????
	}
	
    public Block(int blockId , World world , int x , int y){
    	this.coord = new Coordinates(x , y);
    	this.observer = world;
        this.blockId = blockId;
    }
    
    public Block(int blockId , int strength , World world, int x , int y){
        this(blockId , world, x , y);
        this.strength = strength;
    }
    
    public void destroy(int power) {
    	this.strength -= power;
    }
    public int getStrength() {
    	return this.strength;
    }
    
    public int getBlockId(){
        return this.blockId;
    }
    public Texture getCurrentTexture(){
    	try {
    	return this.textures.get(this.blockId-1).get(currentTexture);
    	}catch(NullPointerException e) {
    		System.out.println(this.blockId + " " + currentTexture);
    		return null;
    	}
    	catch(IndexOutOfBoundsException e) {
    		System.out.println(this.blockId + " " + currentTexture);
    		return null;
    	}
    }
    protected int blockId;

}
