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
	private String line;
	
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
	
	public DataReader loadNextLine() {
		String line = "";
		try {
			String tempLine = reader.readLine();
			if (tempLine == null) tempLine = END_OF_FILE;
			line = tempLine.startsWith(COMMENT_MARKER) ? "" : tempLine;
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.line = line;
		return this;
	}
	
	/**
	 * Gets the data from the line. Ignores the Title
	 * @return the comma-separated data following the Title
	 */
	public String[] getLineAsData() {
		String[] lineTitled = getLineAsTitled();
		String[] data = lineTitled[1].split(DATA_SEPARATION);
		return data;
	}
	
	/**
	 * Gets the line as all associated values. 
	 * Result[0] is the title, if it exists, (null if not) the remainder is the data.
	 * @return all values from the next readable line.
	 */
	public String[] getLineAsValues() {
		String[] result;
		String[] lineTitled = getLineAsTitled();
		String[] data = getLineAsData();
		
		result = new String[data.length+1];
		result[0] = lineTitled[0];
		for(int i = 0; i< data.length; i++) {
			result[i+1] = data[i];
		}

		return result;
	}
	
	/**
	 * Gets the title and remainders of the line
	 * @return The title in position 0, and the full data in position 1 of an array
	 */
	public String[] getLineAsTitled() {
		String[] titled = line.split(NAME_SEPARATION);
		if(titled.length == 1) {
			titled[1] = titled[0];
			titled[0] = null;
		}
		return titled;
	}
	
	/**
	 * get the line of data in its raw form
	 * @return the last line loaded
	 */
	public String getLine() {
		return line;
	}
}
