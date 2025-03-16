package com.taskforge.Task_Forge.Exceptions;

public class InvalidCredentialsException extends AuthException{
    public InvalidCredentialsException(String message){
        super("Invalid credentials");
    }
}
