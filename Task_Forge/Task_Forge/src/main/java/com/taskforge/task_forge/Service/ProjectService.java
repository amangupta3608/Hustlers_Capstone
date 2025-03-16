package com.taskforge.task_forge.Service;

import com.taskforge.task_forge.Exceptions.CompanyNotFoundExceptions;
import com.taskforge.task_forge.Exceptions.ProjectNotFoundExceptions;
import com.taskforge.task_forge.Model.Company;
import com.taskforge.task_forge.Model.Project;
import com.taskforge.task_forge.Repository.CompanyRepository;
import com.taskforge.task_forge.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Project createProject(UUID companyId, Project project){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundExceptions("Company not found"));
        project.setCompany(company);
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(UUID id){
        return projectRepository.findById(id);
    }

    public List<Project> getProjectsByCompany(UUID companyId){
        return projectRepository.findByCompanyId(companyId);
    }

    public Project updateProject(UUID projectId, Project updatedProject){
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundExceptions("Project not found"));
        existingProject.setName(updatedProject.getName());
        return projectRepository.save(existingProject);
    }

    public void deleteProject(UUID projectid){
        if(!projectRepository.existsById(projectid)){
            throw new ProjectNotFoundExceptions("Project not found");
        }
        projectRepository.deleteById(projectid);
    }
}
