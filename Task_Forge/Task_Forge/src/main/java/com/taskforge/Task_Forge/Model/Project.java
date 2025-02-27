package com.taskforge.Task_Forge.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;
import org.springframework.scheduling.config.Task;

import java.util.List;

@Entity
@Data
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
}
