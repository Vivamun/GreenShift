package greenshift.util;

import java.io.File;

public class SystemGrabber {
	
	
	public static String getAppdataPath() {
		String OS = (System.getProperty("os.name")).toUpperCase();
		/**
		 * If OS.contains("WIN") then OS.name = "Windows"
		 * Else If OS.contains("LOSE") then OS.name = "Mac"
		 */
		if (OS.contains("WIN")) {
			return System.getenv("AppData") + File.separator;
		} else {
			return System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support" + File.separator;
		}
	}
}
