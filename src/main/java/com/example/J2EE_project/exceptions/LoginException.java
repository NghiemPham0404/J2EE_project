package com.example.J2EE_project.exceptions;

public class LoginException extends RuntimeException{
    public static final String WRONG_PASSWORD = "Invalid Password";
    public static final String NON_EXIST_USER = "User not found";

    public LoginException(String message){
        super(message);
    }
}
