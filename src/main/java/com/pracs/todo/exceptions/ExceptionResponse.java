package com.pracs.todo.exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

    private String message;
    private boolean successorFailure;
    private HttpStatus status;

    public ExceptionResponse(String message, boolean successorFailure, HttpStatus status) {
        this.message = message;
        this.successorFailure = successorFailure;
        this.status = status;
    }

    public ExceptionResponse(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessorFailure() {
        return successorFailure;
    }

    public void setSuccessorFailure(boolean successorFailure) {
        this.successorFailure = successorFailure;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
