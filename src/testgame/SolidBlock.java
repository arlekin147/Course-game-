package testgame;

import java.io.FileNotFoundException;

public class SolidBlock extends Block{
	SolidBlock(int blockId, int strength , World world, int x , int y){
		super(blockId , strength, world, x , y);
		/*this.blockId = blockId;
		this.strength = 100;*/
	}
	public void destroy(int power) {
		super.destroy(power);
		if(this.strength < 50) {
			this.currentTexture = 1;
		}
		if(this.strength <= 0) {
			try {
				System.out.println(this.coord.x +  " " + this.coord.y);
				this.observer.spawn(new BlockItem (this.observer, new Animation(Block.textures.get(this.blockId-1).get(0)) , this.coord.x + 0.2 , this.coord.y ,
						this.coord.x + 0.7 , this.coord.y + 0.5 , this.blockId));

			}
			catch(Exception e) {
				System.out.println("test item not found");
			}
			}
	}
}
