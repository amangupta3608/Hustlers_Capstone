package com.taskforge.Task_Forge.Repository;

import com.taskforge.Task_Forge.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findProjectId(UUID projectId);
}
