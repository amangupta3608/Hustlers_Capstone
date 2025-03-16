package com.taskforge.Task_Forge.Repository;

import com.taskforge.Task_Forge.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findProjectId(UUID projectId);
}
