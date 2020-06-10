package com.shoppingcart.cartservice.exception;

import java.util.Date;

public class ResponseException {

	private Date timestamp;
	
	public ResponseException(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	private String message;
	private String details;
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
	
}
