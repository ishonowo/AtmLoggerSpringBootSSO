package com.infinity.app.model;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="http://localhost:4200")
public class AuthenticationBean {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AuthenticationBean(String message) {
		this.message = message;
	}

	public AuthenticationBean() {
	}

	@Override
	public String toString() {
		return "AuthenticationBean [message=" + message + "]";
	}

	
}
