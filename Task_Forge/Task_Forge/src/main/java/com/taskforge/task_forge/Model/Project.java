package com.taskforge.task_forge.Model;

import jakarta.persistence.*;
import lombok.Data;
import com.taskforge.task_forge.Model.Task;
import java.util.UUID;
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
    @OneToMany(mappedBy = "project" , cascade = CascadeType.ALL)
    private List<Task> tasks;
}
