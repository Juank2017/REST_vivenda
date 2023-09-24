package com.melilla.REST_vivienda.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

	
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, ex.getMessage());
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}

//	@ExceptionHandler({  })
//	public ResponseEntity<ApiError> handleEntityCreateError(Exception e) {
//		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
//	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiError> handleForbiden(Exception e) {
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, e.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
	}

	@ExceptionHandler({BadCredentialsException.class  })
	public ResponseEntity<ApiError> handleBadRequest(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

	}

//	@ExceptionHandler({  })
//	public ResponseEntity<ApiError> handleNoEncontrado(Exception ex) {
//		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
//	}
}