package com.example.course.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

@ControllerAdvice //fará a interceptação das exceções que acontecerem para fazer um tratamento para exceções do tipo ResourceNotFoundException
public class ResourcesExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class) //notation que faz com que esse método intercepte exceções desse tipo
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		//retorna uma ResponseEntity com o status de erro + body da resposta vai ter um obj do tipo StandardError
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class) //notation que faz com que esse método intercepte exceções desse tipo
	public ResponseEntity<StandardError> dataBase(DatabaseException e, HttpServletRequest request){
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		//retorna uma ResponseEntity com o status de erro + body da resposta vai ter um obj do tipo StandardError
		return ResponseEntity.status(status).body(err);
	}

}
