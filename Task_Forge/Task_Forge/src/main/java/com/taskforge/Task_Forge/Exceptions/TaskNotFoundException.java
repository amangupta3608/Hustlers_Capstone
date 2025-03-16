package com.taskforge.Task_Forge.Exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message){
        super("Task not found!");
    }
}
