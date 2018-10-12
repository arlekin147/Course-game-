package testgame;

public class ItemFabric {
	private World observer;
	public ItemFabric(World observer) {
		this.observer = observer;
	}
	
	public Item getItem(int itemId) {
		return this.getItem(itemId, new Coordinates(0, 0), new Coordinates(0, 0));
	}
	public Item getItem(int itemId, Coordinates ul, Coordinates br) {
		switch(itemId) {
		case 0: return null;
		case 1: return new BlockItem(this.observer, new Animation(Block.textures.get(itemId-1).get(0))
				, ul.x, ul.y, br.x, br.y, 1);  //GROUND
		case 2: return new BlockItem(this.observer, new Animation(Block.textures.get(itemId-1).get(0)), ul.x, ul.y, br.x, br.y, 2); //STONE
		case 1000: return new Tool(this.observer, new Animation(new Texture("res/pickaxe.png")), 0, 0 , 0 , 0, 1000, 5);
		}
		return null;
	}
	
}
