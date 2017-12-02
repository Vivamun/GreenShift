package greenshift;

import java.io.File;
import java.net.URL;

public class Reference {
	public static final String TITLE = "greenshift";
	/**
	 * Where to install all files from.
	 * I.E, where the default files are, and hence, where to download them from.
	 */
	public static final URL DATA_URL = null;
	/**
	 * Where the game is installed, and all files should be located
	 */
	public static final File INSTALL_LOCATION = new File(System.getProperty("user.dir")); 
}
