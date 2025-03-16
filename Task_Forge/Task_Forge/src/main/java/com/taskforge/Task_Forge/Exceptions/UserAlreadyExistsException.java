package com.taskforge.Task_Forge.Exceptions;

public class UserAlreadyExistsException extends AuthException{
    public UserAlreadyExistsException(String message){
        super("User already exists");
    }
}
