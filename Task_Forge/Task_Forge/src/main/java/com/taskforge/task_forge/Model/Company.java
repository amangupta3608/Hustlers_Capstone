package com.taskforge.task_forge.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

import java.util.List;

@Entity
@Table(name = "Companies")
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
