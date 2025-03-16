package com.taskforge.task_forge.Exceptions;

public class ProjectNotFoundExceptions extends RuntimeException{
    public ProjectNotFoundExceptions(String message){
        super("Project not found!");
    }
}
