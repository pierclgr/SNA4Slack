package it.uniba.file.zip;

/**
 * Classe che modella un eccezione generata ogni qualvolta il file specificato
 * non è presente all'interno dello zip.
 */
public final class FileNotInZipException extends Exception {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = -4816401338177385813L;
	/**
	 * Messaggio dell'oggetto eccezione corrente.
	 */
	private final String message;

	/**
	 * Metodo costruttore della classe FileNotInZipException.
	 * 
	 * @param file
	 *            String che rappresenta il file.
	 */
	public FileNotInZipException(final String file) {
		message = file + " cannot be found in this zip archive";
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
