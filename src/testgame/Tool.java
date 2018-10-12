package testgame;

public class Tool extends Item{

	

	int power;
	
	
	
	public Tool(World observer, Animation pic, double ulx, double uly, double brx, double bry, int itemId, int power) {
		super(observer,pic, ulx, uly, brx, bry, itemId);
		this.power = power;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void LMB(double userX, double userY, double x, double y) {
		if((Math.sqrt((userX - x) * (userX - x) +
				((userY - y)*(userY - y))) <= PC.RANGE))
		this.observer.destroy(new Coordinates(x ,  y) , this.power);
	}

	@Override
	public void RMB(double userX, double userY, double x, double y) {}

}
