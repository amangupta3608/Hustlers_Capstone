package com.taskforge.task_forge.Repository;

import com.taskforge.task_forge.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findProjectId(UUID projectId);
}
