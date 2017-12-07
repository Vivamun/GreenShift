package greenshift.world;

import greenshift.util.HashCoder;

public class Pos {

	private final int xPos;
	private final int zPos;
	
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
	
	public int hashCode() {
		HashCoder result = new HashCoder(17,23);
		result.append(xPos);
		result.append(zPos);
		return result.getHash();
	}
	
}
