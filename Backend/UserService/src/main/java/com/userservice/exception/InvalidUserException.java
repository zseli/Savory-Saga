package com.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidUserException extends Exception{
	public InvalidUserException(String msg){
		super(msg);
	}
}
