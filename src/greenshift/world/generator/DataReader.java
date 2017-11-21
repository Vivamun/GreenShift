package greenshift.world.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {
	private final static String NAME_SEPARATION = ": ";
	private final static String DATA_SEPARATION = ", ";
	private final static String COMMENT_MARKER = "#";
	public final static String END_OF_FILE = "E0f";
	
	private final BufferedReader reader;
	
	public DataReader(File readFile) {
		reader = createReader(readFile);
	}
	
	private BufferedReader createReader(File readFile) {
		BufferedReader result = null;
		try {
			result = new BufferedReader(new FileReader(readFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String readLine() {
		String line = "";
		try {
			String tempLine = reader.readLine();
			if (tempLine == null) tempLine = END_OF_FILE;
			line = tempLine.startsWith(COMMENT_MARKER) ? "" : tempLine;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	/**
	 * Gets the data from the next line. Ignores the Title
	 * @return the comma-separated data following the Title
	 */
	public String[] readLineData() {
		String line = readLine();
		String[] callSplit = line.split(NAME_SEPARATION);
		String[] data = callSplit[callSplit.length-1].split(DATA_SEPARATION);
		return data;
	}
	
	/**
	 * Gets the next line in the file as all associated values. 
	 * Result[0] is the title, if it exists, the remainder is the data.
	 * @return all values from the next readable line.
	 */
	public String[] readLineValues() {
		String[] result;
		String line = readLine();
		String[] callSplit = line.split(NAME_SEPARATION);
		String[] data = callSplit[callSplit.length-1].split(DATA_SEPARATION);
		
		boolean hasTitle = callSplit.length > 1;
		if(hasTitle) {
			result = new String[data.length+1];
			result[0] = callSplit[0];
			for(int i = 0; i< data.length; i++) {
				result[i+1] = data[i];
			}
		} else {
			result = data;
		}
		return result;
	}
	
	/**
	 * Gets the title and remainders of the next line
	 * @return The title in position 0, and the full data in position 1 of an array
	 */
	public String[] readLineTitled() {
		return readLine().split(NAME_SEPARATION);
	}
}
