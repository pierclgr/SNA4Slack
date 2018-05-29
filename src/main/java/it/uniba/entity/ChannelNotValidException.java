package it.uniba.entity;

/**
 * Classe che modella l'eccezione da generare ogni qualvolta il channel
 * specificato non � valido.
 */
public final class ChannelNotValidException extends Exception {
	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1530588041264557265L;
	/**
	 * Messaggio dell'oggetto eccezione corrente.
	 */
	private final String message;

	/**
	 * Costruisce il messaggio per l'oggetto eccezione corrente.
	 * 
	 * @param channel
	 *            String che rappresenta uno specifico channel.
	 */
	public ChannelNotValidException(final String channel) {
		message = channel + " is not a valid channel";
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
