package com.example.J2EE_project.exceptions;

public class NotFoundException extends RuntimeException{

    public static String NOT_FOUND = "the resource you requested could not be found";

    public NotFoundException(String message) {
        super(message);
    }
}
