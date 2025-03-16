package com.taskforge.task_forge.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super("User not found!");
    }
}
