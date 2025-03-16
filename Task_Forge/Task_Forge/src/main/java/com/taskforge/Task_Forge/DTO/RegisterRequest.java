package com.taskforge.task_forge.DTO;

import com.taskforge.task_forge.ENUM.Role;
import lombok.Data;


@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
