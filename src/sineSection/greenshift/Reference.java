package sineSection.greenshift;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Reference {
	/**
	 * The title of the game
	 */
	public static final String TITLE = "greenshift";
	/**
	 * Where to install all files from.
	 * I.E, where the default files are, and hence, where to download them from.
	 */
	public static final URL DATA_URL = dataUrl();
	private static final String DATA_STRING = "https://pastebin.com/raw/EtdAWAwD";
	/**
	 * Where the game is installed, and all files should be located
	 */
	public static final File INSTALL_LOCATION = new File(System.getProperty("user.dir") + File.separator + "InstallTest.txt");

	/**
	 * generate the Data Url Field with try catch blocks
	 * @return
	 */
	private static URL dataUrl() {
		URL result = null;
		
		try {
			result = new URL(DATA_STRING);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return result;
	} 
}
