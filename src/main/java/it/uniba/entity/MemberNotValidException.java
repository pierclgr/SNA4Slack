package it.uniba.entity;

public final class MemberNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1738448587126942860L;
	private String message = " is not a valid member";

	public MemberNotValidException(final String member) {
		message = member + message;
	}

	public String getMessage() {
		return this.message;
	}
}
