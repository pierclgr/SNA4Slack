package it.uniba.file;

import java.io.File;

public final class PathManager {
	private PathManager() {

	}

	public static String getAbsolutePath(final String pathOfFile) {
		File f = new File(pathOfFile);
		return f.getAbsolutePath();
	}
}
