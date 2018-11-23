package testgame;


import org.lwjgl.*;

import org.lwjgl.stb.STBEasyFont;
import java.nio.file.*;
import org.lwjgl.util.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random; 
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Р В РЎвЂ™Р В Р’В»Р В Р’ВµР В РЎвЂќР РЋР С“Р В Р’В°Р В Р вЂ¦Р В РўвЂ�Р РЋР вЂљ
 */

public class World{
	PC player;
	Character player2;
	BackGround back;
    Texture texture;
    Camera cam;
	public final ItemFabric iF;
    public final FabricBlock fb;
    ArrayList<Entity> entityes;
    public static final int blockSize = 20;
    public static final int limV  = 1; //Block per second
    static Random gen = new Random();
    Block[][] map;
    final int length = 5000;
    final int height = 1000;
    final int groundLevel = 20;

    
    public World(long window){
		this.iF = new ItemFabric(this);
        this.entityes = new ArrayList<Entity>();
    	fb = new FabricBlock(this);
    	this.map = new Block[height][length];
        int currentBlockId;
        for(int x = 0; x < height; ++x){
                if(x >= groundLevel){
                    currentBlockId = 1;
                }
                else currentBlockId = 0;
                for(int y = 0; y < length; ++y){
                		this.map[x][y] = fb.getBlock(currentBlockId , y , x); //1 - Р В Р’В·Р В Р’ВµР В РЎпїЅР В Р’В»Р РЋР РЏ
                }
               
        }
        
        this.back = new BackGround();
        texture = new Texture("res/test.png");
       // texture = new Animation(3,3,"res/1");
        Coordinates coord = new Coordinates(TestGame.WIDTH/2/World.blockSize,(TestGame.HEIGHT/2  - 52)/World.blockSize);
        this.player = new PC(this , window ,new Animation(3,9,"res/2") , coord , new Coordinates((TestGame.WIDTH/2/World.blockSize + 1.6),TestGame.HEIGHT/2/World.blockSize));
        this.player2 = new Character(this, new Animation(3,9,"res/1") , new Coordinates((TestGame.WIDTH/2 + 40)/World.blockSize,(TestGame.HEIGHT/2  - 51)/World.blockSize) , new Coordinates(((TestGame.WIDTH/2 + 40)/World.blockSize + 1.6),TestGame.HEIGHT/2/World.blockSize + 1),
        		coord);
        this.cam = new Camera(0 , 0);
       
        
        //3.4 / 2.2
    }
    
    public static boolean isAinB(Entity A, Entity B)
    {
    	return true; //TODO
    }
    
    public static boolean isAinB(Coordinates A, Coordinates Bul, Coordinates Bbr)
    {
    	if (A.x >= Bul.x &&
    		A.y	>= Bul.y &&
    		A.x <= Bbr.x &&
    		A.y <= Bbr.y
    	   ) return true;
    	 return false;
    }
    
    public void spawn(Item item) {
    	//TODO ITEM SPAWN
    	this.entityes.add(item);
    }
    
    public void save() {
    	try {
			FileOutputStream file = new FileOutputStream("saves/save1.sv");
			for(Block[] blocks : this.map) {
				for(Block block : blocks) {
					try {
						
							file.write((block.getBlockId() + "").getBytes());
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					file.write("\n".getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void load(){
    	try {
			BufferedReader reader = new BufferedReader(new FileReader("saves/save1.sv"));
			try {
				char[] line;
				this.map = new Block[this.height][this.length];
				
				
					for(int i = 0; i < this.height; ++i) {
						line = reader.readLine().toCharArray();
						for(int j = 0; j < this.length; ++j) {
							if(line[j] != '\n')
								this.map[i][j] = fb.getBlock(line[j], j, i);
							//if(line[j] != '\n')
								//System.out.print(line[j]);
						}
						//System.out.println();
					}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void spawn(Block block) {
    	try {
    	if(this.map[(int)block.coord.y][(int)block.coord.x].getBlockId() == 0)
    		this.map[(int)block.coord.y][(int)block.coord.x] = block;
    	}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}
    }
    
    public void destroy(Coordinates coordOfMouse , int power) {
    	try {
			
			this.map[(int)coordOfMouse.y ][(int)coordOfMouse.x].destroy(power);
			if(this.map[(int)coordOfMouse.y ][(int)coordOfMouse.x].getStrength() <= 0) {
				//System.out.println(world.map[(int)yOfMouse ][(int)xOfMouse].getStrength());
				this.map[(int)coordOfMouse.y ][(int)coordOfMouse.x] =
						this.fb.getBlock(0, (int)coordOfMouse.x , (int)coordOfMouse.y );
			}
		}
	catch(ArrayIndexOutOfBoundsException e) {
		System.out.println(e);
	}
    }
    
    private void physics(Entity smth) {
    	
    
    	try {
   
    			
    	
    	switch(smth.getDerection()) {
    	//LEFT
    	case 0 :
    		if(((this.map[(int)(smth.br.y - 0.1) ][(int)(smth.ul.x + smth.getVX())] instanceof SolidBlock) ||// ul
    			(this.map[(int)(smth.br.y - 1)][(int)(smth.ul.x + smth.getVX())] instanceof SolidBlock )   ||
    			(this.map[(int)(smth.ul.y + 0.1)][(int)(smth.ul.x + smth.getVX())] instanceof SolidBlock ) ||
    			(this.map[(int)((smth.ul.y + 1))][(int)(smth.ul.x + smth.getVX())] instanceof SolidBlock)))
    					//smth.setVX(-0.1);
					//else 
    		{
						smth.setVX(0);
						//System.out.println("----SETTING VELOCITY X TO ZER0");
    		}
    		break;
    		//RIGHT
    	case 1:
    		if((  (this.map[(int)(smth.br.y - 0.1) ][(int)(smth.br.x + smth.getVX())] instanceof SolidBlock) ||
			      (this.map[(int)(smth.br.y - 1)][(int)(smth.br.x + smth.getVX())] instanceof SolidBlock)    ||
			      (this.map[(int)(smth.ul.y + 0.1)][(int)(smth.br.x + smth.getVX())] instanceof SolidBlock)  ||
				  (this.map[(int)(smth.ul.y + 1)][(int)(smth.br.x + smth.getVX())] instanceof SolidBlock)))
				/*smth.setVX(0.1);
			else */
    		{
				smth.setVX(0);
				//System.out.println("---SOLID BLOCK RIGHT TO ME");
    		}
    		break;
    	case 2 :
	    	{
	        		
	    	}
    		break;
    	case 3 :
    		break;
    		default : {
    		}
    	}
    	
    	if(smth.isJumpNeeded ) {
    		if(!smth.isJumping) 
    			smth.vy -= 27 * Character.currentA;
    		smth.isJumping = true;
    		smth.isJumpNeeded = false;
    	}
    	
    	
    	//UP
    	if (smth.getVY() < 0)
    	//JUMP MODULE
    	if(((this.map[(int)(smth.ul.y + smth.getVY() - 0.01)][(int)(smth.ul.x + 0.1)] instanceof SolidBlock) ||// insctaneof SolidBlock 
   			(this.map[(int)(smth.ul.y + smth.getVY() - 0.01)][(int)(smth.br.x - 0.1)] instanceof SolidBlock ) ||
   			(this.map[(int)(smth.ul.y + smth.getVY() - 0.01)][(int)(smth.ul.x + smth.br.x)/2] instanceof SolidBlock)))
   					//smth.setVX(-0.1);
					//else 
    	{
			smth.setVY(0);
			//System.out.println("----SETTING VELOCITY Y TO ZER0");
    	}
    	
    	//DOWN
    	if(this.map[(int)(smth.br.y + smth.getVY())][(int)(smth.br.x - 0.15)].getBlockId() == 0 &&  //BR
    	   this.map[(int)(smth.br.y + smth.getVY())][(int)(smth.ul.x + 0.15)].getBlockId() == 0 &&
    	   this.map[(int)(smth.br.y + smth.getVY())][(int)(smth.br.x + smth.ul.x)/2].getBlockId() == 0) { 		
    		
    				if (smth.vy <= limV)
    				{ 
    				smth.vy += Character.currentA;
    				//System.out.println("----SETTING VELOCITY Y TO " + smth.vy);
    				}
    				
    				
    			}
    	else{
    		
    		double s =0;
    		if(smth instanceof PC)
    				System.out.println("s = " + s);
    		smth.vy = s;
    		smth.isJumping = false;
    	}
	    	}catch(ArrayIndexOutOfBoundsException e) {
	    		smth.setVX(0);
	    		smth.setVY(0);  
	    	}
    	
    	
    	
    	
    	smth.move();
    	
    }
    
   /* void moveCam(double x, double y){
       this.player.control();
   }*/
    
    public void draw(){    
    	
    	if(this.cam.getY() < 20)
    		this.back.draw(0);
    	else this.back.draw(1);
       

        
    	for(int i = (int)Math.floor(cam.getX()); i <= Math.ceil(cam.getX()+Camera.width); ++i) {
        	for(int j = (int)Math.floor(cam.getY()); j <= Math.ceil(cam.getY()+Camera.height); ++j) {
        		try {
	        		if(map[j][i].getBlockId() != 0) {
	        			map[j][i].getCurrentTexture().bind();
	        			
			                glBegin(GL_QUADS); 
			                glTexCoord2i(0,0);
			                glVertex2d(i-cam.getX(),j-cam.getY());
			                glTexCoord2i(1,0);
			                glVertex2d(i-cam.getX(),j + 1-cam.getY());
			                glTexCoord2i(1,1);
			                glVertex2d(i + 1-cam.getX(),j + 1-cam.getY());
			                glTexCoord2i(0,1);
			                glVertex2d(i + 1-cam.getX(),j-cam.getY());
	        			
		                glEnd();
	        		}
        	}catch(ArrayIndexOutOfBoundsException e) {
				//POHUY
			}
        	}
    	}
    	for(Entity smth : this.entityes) {
    		/*if( (int)Math.floor(cam.getY()) <= smth.ul.y && smth.ul.y <= Math.ceil(cam.getY()+Camera.height)
    				&&
    				(int)Math.floor(cam.getX()) <= smth.ul.x && smth.ul.x <= Math.ceil(cam.getX()+Camera.height))*/
    			smth.draw(cam);
    	}
    	
      
       this.player.draw();
       this.player2.draw(cam);
       
       
    }

    public void update() {
    	
    	int toDelete = -1;
    	this.player.control(this);
    	this.player2.update();
    	//this.player2.move(0.01 , 0);
    	this.physics(player);
    	this.physics(player2);
    	for(Entity smth : this.entityes) {
    		if(smth instanceof Item) {
	    		this.physics(smth);
	    		//System.out.println("Координаты штуки " + smth.getCoord().y + "  "+ player.getCoord().y );
	    		if (Math.sqrt((player.ul.x - smth.ul.x) * (player.ul.x - smth.ul.x) +
						((player.ul.y - smth.ul.y)*(player.ul.y - smth.ul.y))) <= 3)
	    		{
	    			toDelete = (this.entityes.indexOf(smth));
	    		}
    		}
    	}
    	if ((toDelete >= 0)&&this.entityes.size()!=0) {
    		this.player.giveItem((Item)this.entityes.get(toDelete));
    		this.entityes.remove(toDelete);
    	}
    	
    	this.cam.move(player.getVX(), player.getVY());
    	
    	//cam.move(0.1, 0);
    }
    
    public void generateCaves(){
        final int CAVEGENERATEPROCENTHIGH = 1001;
        final int CAVEGENERATEPROCENT = 25;
        
        for(int x = 0; x < height; ++x){
            if(x >= groundLevel){
                for(int y = 0; y < length; ++y){
                    if(gen.nextInt(CAVEGENERATEPROCENTHIGH) < CAVEGENERATEPROCENT/* +(
                        height - x)*/){
                        this.makeCave(x, y);
                    }
                }
            }
        }
    }
    public void generateHills(){
        final int HILLSGENERATEPROCENTHIGH = 1001;
        final int HILLSGENERATEPROCENT = 200;
        for(int x = groundLevel; x >= 0; --x){
                for(int y = 0; y < length; ++y){
                    if(this.map[x][y].getBlockId() == 1 && 
                    (gen.nextInt(HILLSGENERATEPROCENTHIGH) < HILLSGENERATEPROCENT /*+ 
                        (height - x)*/)){
                        this.makeHill(x, y);
                    }
                }
        }
    }
    public void mapCOut(){
        for(int x  = 0; x < height; ++x){
            for(int y = 0; y < length; ++y){
               // System.out.print(this.map[x][y].getBlockId());
            }
           // System.out.println();
        }
    }

    private void makeCave(int x, int y){
        try{
            int[][] pattern = getCavePattern(gen.nextInt(3));
            for(int i = x; i < x + pattern.length; ++i){
                for(int j = y; j < y + pattern[i - x].length; ++j){
                    this.map[i][j] = fb.getBlock(pattern[i - x][j - y],  j , i); // new Block(pattern[i - x][j - y]); //0 Р В Р вЂ Р В РЎвЂўР В Р’В·Р В РўвЂ�Р РЋРЎвЂњР РЋРІР‚В¦
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e){

        }
    }
    private static int[][] getCavePattern(int num){
        int[][] pattern = null;
        switch(num){
            case 0:{
                pattern = new int[3][3];
                for(int i = 0; i < 3; ++i){
                    for(int j = 0; j < 3; ++j){
                        pattern[i][j] = 0;
                    }
                }
                break;
            }
            case 1 :{
                pattern = new int[5][5];
                for(int i = 0; i < 5; ++i){
                    for(int j = 0; j < 5; ++j){
                        if(i == 2 && j == 2) pattern[i][j] = 2;
                        else pattern[i][j] = 0;
                    }
                }
                break;
            }
            case 2:{
                pattern = new int[3][3];
                for(int i = 0; i < 3; ++i){
                    for(int j = 0; j < 3; ++j){
                        if((i == 0)
                            || j == 1) pattern[i][j] = 1;
                        else pattern[i][j] = 0;
                    }
                }
                break;
            }
        }
        return pattern;
    }
    
    private static int[][] getHillPattern(int num){
        int[][] pattern = null;
        switch(num){
            case 0:{
                pattern = new int[3][3];
                for(int i = 0; i < 3; ++i){
                    for(int j = 0; j < 3; ++j){
                        pattern[i][j] = 1;
                    }
                }
                break;
            }
            case 1 :{
                pattern = new int[5][5];
                for(int i = 0; i < 5; ++i){
                    for(int j = 0; j < 5; ++j){
                        if(i == 2 && j == 2) pattern[i][j] = 0;
                        else pattern[i][j] = 1;
                    }
                }
                break;
            }
            case 2:{
                pattern = new int[3][3];
                for(int i = 0; i < 3; ++i){
                    for(int j = 0; j < 3; ++j){
                        if((i == 0)
                            || j == 1) pattern[i][j] = 1;
                        else pattern[i][j] = 1;
                    }
                }
                break;
            }
        }
        return pattern;
    }
    
    private void makeHill(int x, int y){
        
        try{
            
            
            int[][] pattern = getHillPattern(gen.nextInt(3));
            switch(gen.nextInt(2)){
                case 0: //right
                    for(int i = x , patternXCount = 0; i > x - pattern.length; --i , ++patternXCount){
                        //System.out.println(pattern.length + " " + pattern[i].length);
                        for(int j = y, patternYCount = 0; j < y + pattern[patternXCount].length; ++j, ++patternYCount){
                            this.map[i][j] = fb.getBlock(pattern[patternXCount][patternYCount] , j , i);  /*new Block(pattern[i - x][j - y])*/
                        }
                    }
                    break;
                case 1: //left
                    for(int i = x , patternXCount = 0; i > x - pattern.length; --i , ++patternXCount){
                        for(int j = y, patternYCount = 0; j > y - pattern[++patternXCount].length; --j , ++patternYCount){
                            this.map[i][j] = fb.getBlock(pattern[patternXCount][patternYCount] , j , i)/*new Block(pattern[i - x][j - y])*/;
                        }
                    }
                    break;
                    
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
        
        	
        	

        }
    }
    public Block[][] getMap(){
        return this.map;
    }

}
