package com.masai.Exception;

import jakarta.persistence.PersistenceException;

public class UserNotFoundException extends PersistenceException{
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
