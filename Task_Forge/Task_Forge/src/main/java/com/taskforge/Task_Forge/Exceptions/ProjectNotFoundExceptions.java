package com.taskforge.Task_Forge.Exceptions;

public class ProjectNotFoundExceptions extends RuntimeException{
    public ProjectNotFoundExceptions(String message){
        super("Project not found!");
    }
}
