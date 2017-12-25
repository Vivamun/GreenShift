package sineSection.greenshift.launcher.parser;

import java.io.File;

import sineSection.greenshift.launcher.FileReader;
import sineSection.greenshift.world.Room;

public class RoomParser extends Parser {

	private static final String NAME_TEXT = "Name";
	private static final String DESC_TEXT = "Description";
	
	
	public Room parse(File readFile) {
		String name = "";
		String description = "";
		
		FileReader reader = new FileReader(readFile);
		String[] data = reader.loadFirstLine().getLineAsValues();
		
		while(!data[1].contains(FileReader.END_OF_FILE)) {
			switch(data[0]) {
			case(NAME_TEXT):
				name = data[1];
				break;
			case(DESC_TEXT):
				description = data[1];
				break;
			}
			data = reader.loadNextLine().getLineAsValues();
		}
		 return new Room(name, description);
	}
}
