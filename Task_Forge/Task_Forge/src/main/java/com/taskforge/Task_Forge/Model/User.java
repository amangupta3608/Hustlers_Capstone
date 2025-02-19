package com.taskforge.Task_Forge.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
}
