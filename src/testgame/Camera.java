/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

/**
 *
 * @author Александр
 */
public class Camera {
    public static final double width = TestGame.WIDTH / World.blockSize;

    public static final double height = TestGame.HEIGHT / World.blockSize;
    private Coordinates coord;
    
    public Camera(double x , double y){
        this.coord = new Coordinates(x , y);
    }
    public Camera(Coordinates coord) {
    	this.coord = coord;
    }
    public double getX(){
        return this.coord.x;
    }
    public double getY(){
        return this.coord.y;
    }
    public void move(double x, double y){
        this.coord.x += x;
        this.coord.y += y;
       /* try {
			//Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
}
