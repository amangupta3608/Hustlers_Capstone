package com.taskforge.task_forge.Exceptions;

public class InvalidCredentialsException extends AuthException{
    public InvalidCredentialsException(String message){
        super("Invalid credentials");
    }
}
