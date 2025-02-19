package com.taskforge.Task_Forge.Service;

import com.taskforge.Task_Forge.Exceptions.ProjectNotFoundExceptions;
import com.taskforge.Task_Forge.Model.Project;
import com.taskforge.Task_Forge.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    public Optional<Project> getProject(Long id){
        return projectRepository.findById(id);
    }

    public Project updateProject(Long id, Project projectDetails){
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundExceptions("Project not found"));
        project.setName(project.getName());
        project.setDescription(project.getDescription());
        return projectRepository.save(project);
    }

    public void deleteProject(Long id){
        if(!projectRepository.existsById(id)){
            throw new ProjectNotFoundExceptions("Project not found");
        }
        projectRepository.deleteById(id);
    }
}
