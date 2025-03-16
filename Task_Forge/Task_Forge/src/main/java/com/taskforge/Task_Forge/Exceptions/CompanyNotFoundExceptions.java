package com.taskforge.Task_Forge.Exceptions;

public class CompanyNotFoundExceptions extends RuntimeException{
    public CompanyNotFoundExceptions(String message){
        super("Company not found!");
    }
}
