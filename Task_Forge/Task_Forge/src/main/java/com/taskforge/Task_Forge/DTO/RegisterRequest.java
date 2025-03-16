package com.taskforge.Task_Forge.DTO;

import com.taskforge.Task_Forge.ENUM.Role;
import lombok.Data;


@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
