package sineSection.greenshift;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

/**
 * The application folder data and thing-a-ma-bobs.
 * 
 * @author Richard Abbott (Richie_TheRich)
 *
 */
public class ApplicationData {

	/**
	 * Gets a file at the specified path WITHIN the application folder.
	 */
	public static File readFile(String path) {
		String filePath = getAppFolderPath() + File.separator + path;
		File file = new File(filePath);
		if (!file.isDirectory() && file.canRead() && file.exists())
			return file;
		else {
			System.err.println("Couldn't read from '" + filePath + "'" + (file.canRead() ? (file.isDirectory() ? ", is a directory." : "") : ", can't access."));
			return null;
		}
	}

	/**
	 * Writes a file at the specified path WITHIN the application folder.
	 */
	public static boolean writeFile(String path, byte[] data) {
		String filePath = getAppFolderPath() + File.separator + path;
		File file = new File(filePath);
		try {
			if (file.canWrite()) {
				if (file.exists())
					file.delete();
				file.createNewFile();
				FileUtils.writeByteArrayToFile(file, data);
				return true;
			} else
				throw new Exception();
		} catch (Exception e) {
			System.err.println("Couldn't write to '" + filePath + "'" + (file.canWrite() ? (file.isDirectory() ? ", is a directory." : "") : ", can't access."));
			return false;
		}
	}

	/**
	 * Checks if a file exists at the specified path WITHIN the application
	 * folder.
	 */
	public static boolean doesFileExist(String path) {
		String filePath = getAppFolderPath() + File.separator + path;
		File file = new File(filePath);
		// if (!file.exists()) return false; // The obvious one.
		if (!file.isFile())
			return false; // Not a directory.
		if (!file.canRead())
			return false; // If ya can't read it, it may as well not exist.
		return true;
	}

	/**
	 * Reads all files in a directory the specified path WITHIN the application
	 * folder.
	 */
	public static File[] getAllFiles(String path) {
		String dirPath = getAppFolderPath() + File.separator + path;
		File dir = new File(dirPath);
		ArrayList<File> files = new ArrayList<File>();
		if (dir.isDirectory() && dir.exists()) {
			for (File f : dir.listFiles()) {
				if (f.isFile())
					files.add(f);
			}
			return files.toArray(new File[files.size()]);
		} else {
			return new File[0];
		}
	}

	/**
	 * @return a path to the application folder
	 */
	public static String getAppFolderPath() {
		if(GreenShift.TESTING) {
			return System.getProperty("user.dir") + File.separator + "res" + File.separator + "data" + File.separator + "test";
		} else {
		String OS = (System.getProperty("os.name")).toUpperCase();
		if (OS.contains("WIN")) {
			// Windahs
			return System.getenv("AppData") + File.separator + Reference.TITLE;
		} else {
			// Mac-and-cheese and also lin-hicks
			return System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support" + File.separator + Reference.TITLE;
		}
		}
	}

}
