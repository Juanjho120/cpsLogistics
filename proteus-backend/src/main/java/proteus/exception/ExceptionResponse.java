package proteus.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
	
	private LocalDateTime dateTime;
	private String message;
	private String details;
	
	/**
	 * Constructor without parameters
	 */
	public ExceptionResponse() {
		this.message = "";
		this.details = "";
	}
	
	/**
	 * Constructor with parameters
	 * 
	 * @param dateTime
	 * @param message
	 * @param details
	 */
	public ExceptionResponse(LocalDateTime dateTime, String message, String details) {
		this.dateTime = dateTime;
		this.message = message;
		this.details = details;
	}

	/**
	 * @return the dateTime
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

}
