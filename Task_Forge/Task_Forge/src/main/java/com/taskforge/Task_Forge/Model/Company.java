package com.taskforge.Task_Forge.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "company")
    private List<Project> projects;
    @OneToMany(mappedBy = "company")
    private List<User> users;
}
