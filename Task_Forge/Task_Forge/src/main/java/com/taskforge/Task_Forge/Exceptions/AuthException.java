package com.taskforge.Task_Forge.Exceptions;

public class AuthException extends RuntimeException{

    public AuthException(String message){
        super("Authorization Error");
    }

}
