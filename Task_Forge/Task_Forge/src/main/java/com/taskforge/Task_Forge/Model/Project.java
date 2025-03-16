package com.taskforge.Task_Forge.Model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.UUID;
import com.taskforge.Task_Forge.Model.Task;

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
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
