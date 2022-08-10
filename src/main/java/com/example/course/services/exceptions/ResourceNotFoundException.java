package com.example.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) { //recebe o id do obj que tentei encontrar e não encontrei
		super("Resource not found. Id " + id);
	}

}
