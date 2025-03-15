package com.taskforge.Task_Forge.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.taskforge.Task_Forge.ENUM.Role; // Correct import here
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Entity
@Data
@Table(name = "users")
public class User {
    @Id

    private UUID id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
//    @ManyToOne
//    @JoinColumn(name = "company_id")
//   // @JsonDeserialize(using = Company.class)
//    private Company company;

    @Enumerated(EnumType.STRING)
    private Role role; // Now using the correct Role enum
}