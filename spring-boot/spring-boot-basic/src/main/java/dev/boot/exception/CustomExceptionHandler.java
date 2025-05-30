package dev.boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

// (3)
@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handlerErrorException(NotFoundException ex, WebRequest red) {
		return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

}
