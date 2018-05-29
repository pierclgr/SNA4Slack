package it.uniba.file.zip;

/**
 * Classe che modella l'eccezione da generare ogni qualvolta il file passato non
 * � uno zip file.
 */
public final class NotZipFileException extends Exception {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 2202197388729548553L;
	/**
	 * Messaggio dell'oggetto eccezione corrente.
	 */
	private final String message;

	/**
	 * Metodo costruttore della classe NotZipFileException.
	 * 
	 * @param file
	 *            String che rappresenta il file.
	 */
	public NotZipFileException(final String file) {
		message = file + " is not a zip file";
	}

	/**
	 * Restituisce il messaggio dell'oggetto eccezione corrente.
	 * 
	 * @return String che rappresenta il messaggio dell'oggetto eccezione corrente.
	 */
	public String getMessage() {
		return this.message;
	}
}
