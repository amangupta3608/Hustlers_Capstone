package com.taskforge.Task_Forge.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
