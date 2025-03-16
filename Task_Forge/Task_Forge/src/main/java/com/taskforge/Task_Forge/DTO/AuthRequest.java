package com.taskforge.task_forge.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}