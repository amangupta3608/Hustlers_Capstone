package com.taskforge.Task_Forge.Service;

import com.taskforge.Task_Forge.ENUM.TaskStatus; // Import the enum
import com.taskforge.Task_Forge.Exceptions.TaskNotFoundException;
import com.taskforge.Task_Forge.Exceptions.UserNotFoundException;
import com.taskforge.Task_Forge.Model.Project;
import com.taskforge.Task_Forge.Model.Task;
import com.taskforge.Task_Forge.Model.User;
import com.taskforge.Task_Forge.Repository.ProjectRepository;
import com.taskforge.Task_Forge.Repository.TaskRepository;
import com.taskforge.Task_Forge.Repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElse(null);
        if(task == null){
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        return task;
    }

    @Transactional
    public Task createTask(UUID projectId, UUID userId, Task task) {
        if (projectId == null) {
            throw new IllegalArgumentException("Project ID cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (task == null) {
            throw new IllegalArgumentException("Task object cannot be null");
        }
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be null or empty");
        }
        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Task status cannot be null");
        }

        Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null) {
            throw new TaskNotFoundException("Project not found with id: " + projectId);
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        task.setProject(project);
        task.setAssignedUser(user);
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTaskStatus(UUID taskId, TaskStatus status) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Task status cannot be null");
        }

        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            throw new TaskNotFoundException("Task not found with id: " + taskId);
        }

        task.setStatus(status);
        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(UUID taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }
        if (!taskRepository.existsById(taskId)) {
            throw new TaskNotFoundException("Task not found with id: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }
}