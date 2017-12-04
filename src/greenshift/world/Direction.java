package greenshift.world;

public enum Direction {
	FORE(0,-1),
	AFT(0,1),
	PORT(-1,0),
	STARBOARD(1,0);
	

	final int delX;
	final int delZ;
	
	Direction(int delX, int delZ) {
		this.delX = delX;
		this.delZ = delZ;
	}
}
