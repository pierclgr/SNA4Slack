package it.uniba.file;

import java.io.File;

/**
 * Classe che permette di gestire i percorsi relativi ed assoluti.
 */
public final class PathManager {
	/**
	 * Metodo costruttore della classe PathManager.
	 */
	private PathManager() {

	}

	/**
	 * Restituisce il percorso assoluto di un file.
	 * @param pathOfFile String che rappresenta il percorso di un determinato file.
	 * @return String che rappresenta il percorso assoluto di un file.
	 */
	public static String getAbsolutePath(final String pathOfFile) {
		File f = new File(pathOfFile);
		return f.getAbsolutePath();
	}
}
