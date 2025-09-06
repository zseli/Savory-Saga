package com.jwtauthentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidRecipeException extends Exception{

	public InvalidRecipeException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
}
