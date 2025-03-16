package com.taskforge.task_forge.Exceptions;

public class AuthException extends RuntimeException{

    public AuthException(String message){
        super("Authorization Error");
    }

}
