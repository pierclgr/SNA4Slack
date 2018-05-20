package it.uniba.file.zip;

public final class NotZipFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2202197388729548553L;
	private String message = " is not a zip file";

	public NotZipFileException(final String file) {
		message = file + message;
	}

	public String getMessage() {
		return this.message;
	}
}
