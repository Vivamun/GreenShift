package greenshift.world;
//TODO: Implement Pos class
public class Pos {

	final int xPos;
	final int zPos;
	
	public Pos(int x, int z) {
		this.xPos = x;
		this.zPos = z;
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getZ() {
		return zPos;
	}
	
	public Pos move(Direction dir) {
		return new Pos(xPos + dir.getDelX(), zPos + dir.getDelZ());
	}
	
}
