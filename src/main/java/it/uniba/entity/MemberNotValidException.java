package it.uniba.entity;

/**
 * Classe che modella l'eccezione da generare ogni qualvolta il member
 * specificato non � valido.
 */
public final class MemberNotValidException extends Exception {
	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1738448587126942860L;
	/**
	 * Messaggio dell'oggetto eccezione corrente.
	 */
	private final String message;

	/**
	 * Costruisce il messaggio per l'oggetto eccezione corrente.
	 * 
	 * @param member
	 *            String che rappresenta un member.
	 */
	public MemberNotValidException(final String member) {
		message = member + " is not a valid member";
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
