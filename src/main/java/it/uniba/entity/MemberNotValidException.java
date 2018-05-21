package it.uniba.entity;

/**
 * Classe che modella l'eccezione da generare ogni qualvolta il member
 * specificato non è valido.
 */
public final class MemberNotValidException extends Exception {
	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1738448587126942860L;
	/**
	 * Messaggio dell'oggetto eccezione corrente.
	 */
	private String message = " is not a valid member";

	/**
	 * Costruisce il messaggio per l'oggetto eccezione corrente.
	 * 
	 * @param member
	 *            String che rappresenta un member.
	 */
	public MemberNotValidException(final String member) {
		message = member + message;
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
