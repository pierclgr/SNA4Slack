package it.uniba.file;

import java.io.File;

public class PathManager {
	public static String getAbsolutePath(String pathOfFile) {
		File f=new File(pathOfFile);
		return f.getAbsolutePath();
	}
}
