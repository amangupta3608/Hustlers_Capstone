package com.taskforge.task_forge.Repository;

import com.taskforge.task_forge.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Project> findByCompanyId(UUID companyId);
}
