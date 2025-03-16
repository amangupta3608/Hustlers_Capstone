package com.taskforge.Task_Forge.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private LocalDateTime createdAt;
}
