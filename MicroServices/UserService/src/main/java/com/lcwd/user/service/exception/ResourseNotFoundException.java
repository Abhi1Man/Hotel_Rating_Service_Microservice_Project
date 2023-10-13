package com.lcwd.user.service.exception;

public class ResourseNotFoundException extends RuntimeException{
    public ResourseNotFoundException(){
        super("Resource Not Found on Server !!");
    }

    public ResourseNotFoundException(String message){
        super(message);
    }
}
