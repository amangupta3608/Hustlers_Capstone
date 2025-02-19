package com.taskforge.Task_Forge.Exceptions;

public class InvalidCredentialsExceptions extends AuthException{
    public InvalidCredentialsExceptions(String message){
        super("Invalid credentials");
    }
}
