package com.gregory.leilao.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.gregory.leilao.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> notFound(NotFoundException ex, WebRequest request){
		ErrorResponse responseError = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
				"Não encontrado", ex.getMessage(), request.getDescription(false), null);
		return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> business(BusinessException ex, WebRequest request){
		ErrorResponse responseError = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro de negócio", ex.getMessage(), request.getDescription(false), null);
		return new ResponseEntity<>(responseError, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException ex, WebRequest request){
		List<String> erros = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + ":" + err.getDefaultMessage())
				.collect(Collectors.toList());
		ErrorResponse responseError = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				"Erro de Validação", "Campos Inválidos", request.getDescription(false), erros);
		return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResponse> credenciais(BadCredentialsException ex, WebRequest request){
		ErrorResponse responseError = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Erro Interno", ex.getMessage(), request.getDescription(false), null);
		return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorResponse> credenciais(LoginException ex, WebRequest request){
		ErrorResponse responseError = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				"Erro Interno", ex.getMessage(), request.getDescription(false), null);
		return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> global(Exception ex, WebRequest request){
		ErrorResponse responseError = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Erro Interno", ex.getMessage(), request.getDescription(false), null);
		return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
