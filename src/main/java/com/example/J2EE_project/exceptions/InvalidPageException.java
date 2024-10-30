package com.example.J2EE_project.exceptions;

public class InvalidPageException extends RuntimeException{
    public static String PAGE_NOT_LESS_THAN_ONE  = "Page index must not be less than one";
    public static String OUT_OF_BOUNDS  = "Page index is out of bounds";

    public InvalidPageException(String message){
        super(message);
    }
}
