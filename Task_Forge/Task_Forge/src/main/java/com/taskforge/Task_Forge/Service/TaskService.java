package com.taskforge.Task_Forge.Service;


import com.taskforge.Task_Forge.Exceptions.TaskNotFoundException;
import com.taskforge.Task_Forge.Exceptions.UserNotFoundException;
import com.taskforge.Task_Forge.Model.Project;
import com.taskforge.Task_Forge.Model.Task;
import com.taskforge.Task_Forge.Model.User;
import com.taskforge.Task_Forge.Repository.ProjectRepository;
import com.taskforge.Task_Forge.Repository.TaskRepository;
import com.taskforge.Task_Forge.Repository.UserRepository;
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
