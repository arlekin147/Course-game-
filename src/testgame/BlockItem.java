package testgame;
import java.util.Date;

public class BlockItem extends Item implements Stackable{
	public final static int MAXSTACK = 255;
	int sizeOfStack = 1;
	private Date timeOfLastRemove;
	
	public BlockItem(World observer,Animation pic, double ulx, double uly, double brx, double bry, int itemId) {
		super(observer,pic, ulx, uly, brx, bry, itemId);
		this.timeOfLastRemove = new Date();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void LMB(double userX , double userY ,double x, double y) {}

	@Override
	public void RMB(double userX , double userY ,double x, double y) {
		if(new Date().getTime() - timeOfLastRemove.getTime() > 150) {
			this.timeOfLastRemove = new Date();
			this.getElem();
			this.observer.spawn(observer.fb.getBlock(itemId, (int)x, (int)y));
		}
	}
	
	public boolean stack() {
		if(this.sizeOfStack < BlockItem.MAXSTACK) {
			System.out.println(this.sizeOfStack);
			++this.sizeOfStack;
			return true;
		}
		return false;
	}
	
	public boolean getElem() {
		if(!this.isEmpty()) {
			System.out.println(this.sizeOfStack);
			--this.sizeOfStack;
			return true;
		}
		return false;
	}
	
	public boolean isEmpty() {
		return this.sizeOfStack == 0;
	}
	
	public void collected()
	{
		this.move(observer.player.getCoord().x,observer.player.getCoord().y);
	}
}
