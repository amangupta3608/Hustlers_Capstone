package com.taskforge.Task_Forge.Service;

import com.taskforge.Task_Forge.Exceptions.CompanyNotFoundExceptions;
import com.taskforge.Task_Forge.Exceptions.ProjectNotFoundExceptions;
import com.taskforge.Task_Forge.Model.Company;
import com.taskforge.Task_Forge.Model.Project;
import com.taskforge.Task_Forge.Repository.CompanyRepository;
import com.taskforge.Task_Forge.Repository.ProjectRepository;
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
