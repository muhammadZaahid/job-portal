package com.lawencon.candidate.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lawencon.candidate.dto.ErrorResDto;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResDto<String>> handleBadCredentials(BadCredentialsException bce) {
		final ErrorResDto<String> response = new ErrorResDto<>();
		response.setMessage("Error! Wrong username / password!");
		
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResDto<List<String>>> handleMethoArgumentNotValidException(MethodArgumentNotValidException exception) {
		final List<String> errorResponses = exception
				.getBindingResult().getFieldErrors()
				.stream()
				.map(v -> v.getDefaultMessage())
				.collect(Collectors.toList());
		
		final ErrorResDto<List<String>> responses = new ErrorResDto<>();
		responses.setMessage(errorResponses);
		return new ResponseEntity<>(responses, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResDto<String>> handleRuntimeException(RuntimeException exception){
		final ErrorResDto<String> response = new ErrorResDto<>();
		response.setMessage(exception.getMessage());

		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
}
