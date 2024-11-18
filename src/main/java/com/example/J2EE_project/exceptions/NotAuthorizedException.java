package com.example.J2EE_project.exceptions;

public class NotAuthorizedException extends RuntimeException{
    public static final String NOT_AUTHORIZED = "you are not allowed to access this resources";

    public NotAuthorizedException(String message){
        super(message);
    }
}
