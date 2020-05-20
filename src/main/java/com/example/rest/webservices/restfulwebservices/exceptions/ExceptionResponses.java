package com.example.rest.webservices.restfulwebservices.exceptions;

import java.util.Date;

public class ExceptionResponses {
	private Date dateStamp;	
	private String message;
	private String details;
	
	public ExceptionResponses(Date dateStamp, String message, String details) {
		super();
		this.dateStamp = dateStamp;
		this.message = message;
		this.details = details;
		
	}
	
	public Date getDateStamp() {
		return dateStamp;
	}
	public void setDateStamp(Date dateStamp) {
		this.dateStamp = dateStamp;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
