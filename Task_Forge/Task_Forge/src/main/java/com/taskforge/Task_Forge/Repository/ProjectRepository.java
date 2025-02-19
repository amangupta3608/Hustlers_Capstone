package com.taskforge.Task_Forge.Repository;

import com.taskforge.Task_Forge.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
