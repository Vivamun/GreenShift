package greenshift;

import java.util.Random;

public class GreenShift {

	public static final boolean LAUNCHABLE = false;

	public static void main(String[] args) {
		try {
			System.out.print("INITATING LAUNCH SEQUENCE");
			Thread.sleep(1000);
			System.out.print(".");
			Thread.sleep(1000);
			System.out.print(".");
			Thread.sleep(1000);
			System.out.println(".");
			Thread.sleep(1000);
			if (LAUNCHABLE)
				System.out.println("The S.S. Greenshift has been launched. Have a pleasant trip.");
			else {
				System.out.println("ERROR IN LAUNCH! CODE: 0x" + Integer.toHexString(new Random().nextInt()).toUpperCase()
						+ ", SHORT: \"ERR_NYI_LAUNCH\", FULL: \"Launch Simulation Unsucessful. Please implement launch sequence.\"");
				Thread.sleep(500);
				System.out.println("ABORTING!");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
