package testgame;

public class Character extends Entity {
		
		
		protected int MaxHealth;
		protected int health;
		protected Coordinates pcCoord;
		protected final static double currentA = 0.01;
		public Character(World observer,Animation pic, Coordinates ne,  Coordinates sw){
	    	super(observer,pic, ne , sw);
	    	this.MaxHealth = this.health = 100;
	    	
	    }
		
	    public Character(World observer,Animation pic, int ulx,int uly,int brx, int bry){
	    
	       this(observer,pic,new Coordinates(ulx,uly),new Coordinates(brx,bry));
	    }
	    
	    public Character(World observer,Animation pic, Coordinates ul, Coordinates br , Coordinates coord) {
	    	this(observer,pic,ul,br);
	    	this.pcCoord = coord;
	    }
	    
	    private void makeDamage(Coordinates coordOfAttack) {
	    	
	    }
	    
	    public void move(double x, double y)
	    {
	    	//System.out.println(x + " " + y);
	    	this.ul.x += x;
	    	this.ul.y += y;
	    	this.br.x += x;
	    	this.br.y += y;
	    	
	    	//System.out.println("(" + ul.x + "," + ul.y + ") (" + br.x + "," + br.y + ") ");	    	
	    }
	    
	   
	  

		@Override
		public void update() {
			if(this.pcCoord != null) {
				if(this.pcCoord.x < this.ul.x ) {
					this.isMove = true;
					this.vx = -0.05;
					this.derection = 0;
				}else if(this.pcCoord.x > this.ul.x) {
					this.isMove = true;
					this.vx = 0.05;
					this.derection = 1;
				}
			}
		}

	    
	    
	    
	    
	    

}


		
