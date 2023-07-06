package com.charter.api.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ResponseExceptionHandler extend ResponseEntityExceptionHandler{

 private static String BAD_REQUEST_MESSAGE = "Bad Request";
 private static String UNAUTHORIZED_MESSAGE = "User is unauthorized";
 private static String FORBIDDEN_MESSAGE = "Action is forbidden";
 private static String NOT_FOUND_MESSAGE = "Not found";
 private static String INTERNAL_ERROR_MESSAGE = "An internal error just created";

@ExceptionHandler({Exception.class})
protected ResponseEntity handleException(Exception exception){

    ErrorMessage errorMessage = ErrorMessage.builder().timestamp(LocalDateTime.now()).build();

    String errorText = exception.getMessage();

    errorMessage.setMessage(errorText);
    errorMessage.setStatus(exception.getErrorCode());

    return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);

}




}