package com.masai.Exception;

import jakarta.persistence.PersistenceException;

public class SomethingWentWrongException extends PersistenceException {
	
	public SomethingWentWrongException(String message) {
		super(message);
	}

}
