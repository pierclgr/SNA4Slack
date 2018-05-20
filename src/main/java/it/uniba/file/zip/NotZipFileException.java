package it.uniba.file.zip;

public class NotZipFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2202197388729548553L;
	private String message=" is not a zip file";
	
	public NotZipFileException(String file) {
		message=file+message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
