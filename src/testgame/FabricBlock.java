package testgame;

import java.util.*;

public class FabricBlock {
	World world;
	public FabricBlock(World world) {
		this.world = world;
		Block.textures.add(new ArrayList<Texture>());
		Block.textures.get(0).add(new Texture("res/test.png"));
		Block.textures.get(0).add(new Texture("res/test2.png"));
		Block.textures.add(new ArrayList<Texture>());
		Block.textures.get(1).add(new Texture("res/stone1.png"));
		Block.textures.get(1).add(new Texture("res/stone2.png"));
		System.out.println(Block.textures.size() + " " + Block.textures.get(0).size());
	}
	public Block getBlock(int num ,int x ,int y) {
		switch(num) {
		case 0 : {
			return new Block(0 , world, x, y);
		}
		case 1 : {
			SolidBlock sb = new SolidBlock(1, 100 , world, x, y);
			return sb;
		}
		case 2:{
			SolidBlock sb = new SolidBlock(2, 150, world, x, y);
			return sb;
		}
		default : return new Block(0, world, x, y);
		}
	}
}
