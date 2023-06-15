package com.pracs.todo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e){
        logger.info("Null pointer exception from Global Exception Handler" );

        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //unchecked exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerResourceHandler(ResourceNotFoundException ex){
        logger.error("ERROR: {}", ex.getMsg());
        ExceptionResponse response = new ExceptionResponse();

        response.setMessage(ex.getMsg());
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setSuccessorFailure(false);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
}
