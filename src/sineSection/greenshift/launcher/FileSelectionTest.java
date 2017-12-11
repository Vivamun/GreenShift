package sineSection.greenshift.launcher;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileSelectionTest{
	
	public static void main(String[] args) {
		new FileSelectionTest();
	}

	public FileSelectionTest() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if(fc.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
			System.out.println(fc.getSelectedFile());
			//use as save file to load this game
		} else {
			System.out.println("Selection cancelled by User");
			//ask to start new game
		}
		
	}
	
}
