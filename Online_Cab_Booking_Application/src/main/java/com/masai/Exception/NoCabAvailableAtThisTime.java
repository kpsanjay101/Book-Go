package com.masai.Exception;

import jakarta.persistence.PersistenceException;

public class NoCabAvailableAtThisTime extends PersistenceException{
	
	public NoCabAvailableAtThisTime(String message) {
		super(message);
	}

}
