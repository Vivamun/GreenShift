package greenshift.launcher;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileSelectionTest{
	
	public static void main(String[] args) {
		new FileSelectionTest();
	}

	public FileSelectionTest() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(fc.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
			System.out.println(fc.getSelectedFile());
		} else {
			System.out.println("Selection cancelled by User");
		}
		
	}
	
}
