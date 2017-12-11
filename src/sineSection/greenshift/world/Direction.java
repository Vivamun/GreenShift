package sineSection.greenshift.world;

public enum Direction {
	FORE(0,-1),
	AFT(0,1),
	PORT(-1,0),
	STARBOARD(1,0);
	
	final private int delX;
	final private int delZ;
	
	Direction(int delX, int delZ) {
		this.delX = delX;
		this.delZ = delZ;
	}
	
	public int getDelX() {
		return delX;
	}
	
	public int getDelZ() {
		return delZ;
	}
	
	public Direction getOpposite() {
		Direction opposite = null;
		switch(this) {
		case FORE:
			opposite = AFT;
			break;
		case AFT:
			opposite = FORE;
			break;
		case PORT:
			opposite = STARBOARD;
			break;
		case STARBOARD:
			opposite = PORT;
			break;
		}
		return opposite;
	}
}
