package it.uniba.file.zip;

public final class FileNotInZipException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4816401338177385813L;
	private String message = " cannot be found in this zip archive";

	public FileNotInZipException(final String file) {
		message = file + message;
	}

	public String getMessage() {
		return this.message;
	}
}
