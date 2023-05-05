package com.masai.Exception;

import jakarta.persistence.PersistenceException;

public class NoRecordFoundException extends PersistenceException{
	
	public NoRecordFoundException(String message) {
		super(message);
	}

}
