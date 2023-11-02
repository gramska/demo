package com.ago.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({NotFoundException.class})
	@ResponseBody
	public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception,request.getRequestURI());
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ConflictException.class})
	@ResponseBody
	public ErrorMessage conflictRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception,request.getRequestURI());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
		BadRequestException.class,
		org.springframework.web.HttpRequestMethodNotSupportedException.class,
		org.springframework.web.bind.MethodArgumentNotValidException.class,
		org.springframework.web.bind.MissingRequestHeaderException.class,
		org.springframework.web.bind.MissingServletRequestParameterException.class,
		org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
		org.springframework.http.converter.HttpMessageNotReadableException.class
	})
	@ResponseBody
	public ErrorMessage babMessage(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception,request.getRequestURI());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public ErrorMessage fatalErrorUnexpectedException(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception,request.getRequestURI());
	}
}
