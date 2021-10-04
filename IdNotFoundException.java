package com.technoelevate.hibernate;

public class IdNotFoundException extends RuntimeException {
	
	public String getMessage()
	{
		return "ID Is Not Found Enter Correct ID";
	}
}
