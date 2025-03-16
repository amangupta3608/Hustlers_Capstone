package com.taskforge.task_forge.Exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message){

        super("Task not found!");
    }
}
