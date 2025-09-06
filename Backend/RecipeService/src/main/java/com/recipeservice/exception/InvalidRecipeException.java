package com.recipeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRecipeException extends Exception{
	public InvalidRecipeException(String msg){
		super(msg);
	}
}
