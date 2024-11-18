package com.example.J2EE_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleCloudVendorNotFoundException(NotFoundException exception) {
        AppException appException = new AppException(exception.getMessage(), null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(appException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InvalidPageException.class})
    public ResponseEntity<Object> handleInvalidPageException(InvalidPageException exception) {
        AppException appException = new AppException(exception.getMessage(), null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(appException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {LoginException.class})
    public ResponseEntity<Object> handleLoginException(LoginException exception) {
        AppException appException = new AppException(exception.getMessage(), null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(appException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotAuthorizedException.class})
    public ResponseEntity<Object> handleNotAuthorizedException(NotAuthorizedException exception) {
        AppException appException = new AppException(exception.getMessage(), null, HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(appException, HttpStatus.FORBIDDEN);
    }
}
