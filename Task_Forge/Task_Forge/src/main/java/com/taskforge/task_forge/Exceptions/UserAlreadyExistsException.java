package com.taskforge.task_forge.Exceptions;

public class UserAlreadyExistsException extends AuthException{
    public UserAlreadyExistsException(String message){
        super("User already exists");
    }
}
