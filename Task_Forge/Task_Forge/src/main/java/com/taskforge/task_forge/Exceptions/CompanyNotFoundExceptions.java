package com.taskforge.task_forge.Exceptions;

public class CompanyNotFoundExceptions extends RuntimeException{
    public CompanyNotFoundExceptions(String message){
        super("Company not found!");
    }
}
