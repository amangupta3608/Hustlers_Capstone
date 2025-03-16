package com.taskforge.task_forge.Service;


import com.taskforge.task_forge.Exceptions.TaskNotFoundException;
import com.taskforge.task_forge.Exceptions.UserNotFoundException;
import com.taskforge.task_forge.Model.Project;
import com.taskforge.task_forge.Model.Task;
import com.taskforge.task_forge.Model.User;
import com.taskforge.task_forge.Repository.ProjectRepository;
import com.taskforge.task_forge.Repository.TaskRepository;
import com.taskforge.task_forge.Repository.UserRepository;
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

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(UUID id){
        return taskRepository.findById(id);
    }

    public Task createTask(UUID projectId, UUID userId, Task task){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new TaskNotFoundException("Project not found!"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        task.setProject(project);
        task.setAssignedUser(user);
        return taskRepository.save(task);
    }

    public Task updateTaskStatus(UUID taskId, String status){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        task.setStatus(status);
        return taskRepository.save(task);
    }

    public void deleteTask(UUID taskId){
        if(!taskRepository.existsById(taskId)){
            throw new TaskNotFoundException("Task not found");
        }
        taskRepository.deleteById(taskId);
    }
}
