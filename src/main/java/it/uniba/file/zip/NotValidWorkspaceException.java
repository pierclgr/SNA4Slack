package it.uniba.file.zip;

/**
 * Classe che modella l'eccezione generata ogni qualvolta il workspace
 * specificato non � valido.
 */
public final class NotValidWorkspaceException extends Exception {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 8654253055564465806L;
	/**
	 * Messaggio dell'oggetto eccezione corrente.
	 */
	private final String message;

	/**
	 * Metodo costruttore della classe NotValidWorkspaceException.
	 * 
	 * @param file
	 *            String che rappresenta il file.
	 */
	public NotValidWorkspaceException(final String file) {
		message = file + " is not a valid Slack workspace";
	}

	/**
	 * Restituisce il messaggio relativo all'oggetto eccezione corrente.
	 * 
	 * @return String che rappresenta il messaggio dell'oggetto eccezione corrente.
	 */
	public String getMessage() {
		return this.message;
	}
}
