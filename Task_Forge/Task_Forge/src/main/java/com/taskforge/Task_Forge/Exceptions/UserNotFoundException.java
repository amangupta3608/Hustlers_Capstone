package com.taskforge.Task_Forge.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super("User not found!");
    }
}
