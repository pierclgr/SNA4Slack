package it.uniba.entity;

public class ChannelNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1530588041264557265L;
	private String message=" is not a valid channel";
	
	public ChannelNotValidException(String channel) {
		message=channel+message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}	
