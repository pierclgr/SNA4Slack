package it.uniba.main;

import it.uniba.cli.CommandManager;

/**
 * Classe principale del progetto. Contiene il metodo main da cui viene avviata
 * l'esecuzione dell'applicazione.
 */
public final class AppMain {

	/**
	 * Costruttore della classe AppMain.
	 */
	private AppMain() {

	}

	/**
	 * Metodo da cui viene avviata l'esecuzione dell'applicazione.
	 * 
	 * @param args
	 *            array di String in cui ciascuno String rappresenta un singolo
	 *            argomento passato all'applicazione da riga di comando.
	 */
	public static void main(final String[] args) {
		CommandManager.manage(args);
	}
}
