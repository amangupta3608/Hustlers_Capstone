package com.taskforge.task_forge.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String status; //To-Do, In Progress, Done

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedUser;
}
