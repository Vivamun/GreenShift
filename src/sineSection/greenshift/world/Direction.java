package sineSection.greenshift.world;

public enum Direction {
	FORE("fore", 0, 0, -1),
	AFT("aft", 0, 0, 1),
	PORT("port", -1, 0, 0),
	STARBOARD("starboard", 1, 0, 0);

	final private int delX;
	final private int delZ;
	final private String call;

	Direction(String call, int delX, int delY, int delZ) {
		this.call = call;
		this.delX = delX;
		this.delZ = delZ;
	}

	public int getDelX() {
		return delX;
	}

	public int getDelZ() {
		return delZ;
	}

	public String getCall() {
		return call;
	}

	public Direction getOpposite() {
		Direction opposite = null;
		switch (this) {
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
