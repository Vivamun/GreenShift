package greenshift.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIO {
	
	public static boolean writeBytesToFile(byte[] data, String path) {
		try (FileOutputStream fos = new FileOutputStream(path)) {
			fos.write(data);
			fos.close();
			return true;
		} catch (IOException e) {
			System.out.println("Error writing file: " + e.getMessage());
			return false;
		}
	}
	
	public static byte[] readBytesFromFile(String path) {
		byte[] out;
		try (FileInputStream fis = new FileInputStream(path)) {
			fis.close();
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
			return null;
		}
		return null;
	}

}
