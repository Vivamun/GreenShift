package sineSection.greenshift.install;

import java.io.IOException;

import org.apache.commons.io.FileUtils;

import sineSection.greenshift.Reference;

public class DataInstaller {

	public static void main(String[] args) {
		try {
			FileUtils.copyURLToFile(Reference.DATA_URL, Reference.INSTALL_LOCATION);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
