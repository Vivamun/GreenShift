package sineSection.greenshift.launcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import sineSection.util.LogWriter;

/**
 * 
 * A file reader class to read a single file and make it accessible to parsers.
 * Holds the separation types, and comment markers, as well as the end of file marker.
 *
 * @author geekman9097, Richie_theRich
 */
public class FileReader {
	private final static String NAME_SEPARATION = ": ";
	private final static String DATA_SEPARATION = ", ";
	private final static String COMMENT_MARKER = "#";
	public final static String END_OF_FILE = "E0f";

	private final List<String> lines;
	private int radix = 0;
	private String line;

	public FileReader(File readFile) {
		lines = loadLines(readFile);
	}

	public List<String> loadLines(File readFile) {
		try {
			List<String> tempLines = FileUtils.readLines(readFile, "UTF-8");
			List<String> rem = new ArrayList<String>();
			tempLines.forEach((l) -> {
				if (l.startsWith(COMMENT_MARKER))
					rem.add(l);
			}); // Remove commented lines in the LIST, not as they are read.
			tempLines.removeAll(rem);
			return tempLines;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * load the next line in the file into storage
	 * @return this file reader for ease of accessibility
	 */
	public FileReader loadNextLine() {
		radix++;
		if(radix < lines.size()) {
			line = lines.get(radix);
		} else {
			line = END_OF_FILE;
		}
		return this;
	}

	/**
	 * Gets the data from the line. Ignores the Title
	 * 
	 * @return the comma-separated data
	 */
	public String[] getLineAsData() {
		String[] lineTitled = getLineAsTitled();
		String[] data = lineTitled[1].split(DATA_SEPARATION);
		return data;
	}

	/**
	 * Gets the data from the line. Gets the Title
	 * 
	 * @return the comma-separated data following the Title
	 * @deprecated use getLineAsValues() for intended result, use getLineAsData for actual.
	 */
	public String[] getLineAsTitledData() {
		String[] lineTitled = getLineAsTitled();
		String[] data = lineTitled[1].split(DATA_SEPARATION);
		return data;
	}

	/**
	 * Gets the line as all associated values. Result[0] is the title, if it
	 * exists, (null if not) the remainder is the data.
	 * 
	 * @return all values from the next readable line.
	 */
	public String[] getLineAsValues() {
		String[] result;
		String[] lineTitled = getLineAsTitled();
		String[] data = getLineAsData();

		result = new String[data.length + 1];
		result[0] = lineTitled[0];
		for (int i = 0; i < data.length; i++) {
			result[i + 1] = data[i];
		}
		LogWriter.print(Arrays.toString(result));
		return result;
	}

	/**
	 * Gets the title and remainders of the line
	 * 
	 * @return The title in position 0, and the full data in position 1 of an
	 *         array
	 */
	public String[] getLineAsTitled() {
		String[] titled = line.split(NAME_SEPARATION);
		LogWriter.print(Arrays.toString(titled));
		if (titled.length == 1) { //If There was no title, only data
			String[] temp = new String[2];
			temp[1] = titled[0];
			temp[0] = null;
			titled = temp;
		}
		return titled;
	}

	/**
	 * get the line of data in its raw form
	 * 
	 * @return the last line loaded
	 */
	public String getLine() {
		return line;
	}

	/**
	 * get all lines in this file
	 * @return
	 */
	public List<String> getLines() {
		return lines;
	}

	/**
	 * Go to the first line in the file. Resets the iteration location.
	 * @return this FileReader
	 */
	public FileReader loadFirstLine() {
		radix = 0;
		line = lines.get(radix);
		return this;
	}
}
