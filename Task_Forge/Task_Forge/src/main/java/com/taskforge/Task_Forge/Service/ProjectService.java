package com.taskforge.Task_Forge.Service;

import com.taskforge.Task_Forge.Exceptions.CompanyNotFoundExceptions;
import com.taskforge.Task_Forge.Exceptions.ProjectNotFoundExceptions;
import com.taskforge.Task_Forge.Model.Company;
import com.taskforge.Task_Forge.Model.Project;
import com.taskforge.Task_Forge.Repository.CompanyRepository;
import com.taskforge.Task_Forge.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Project createProject(UUID companyId, Project project){
        Company company = companyRepository.findById(companyId).orElse(null);
        if(company == null){
            throw new CompanyNotFoundExceptions("Company not found");
        }
        project.setCompany(company);
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Project getProjectById(UUID id){
        Project project = projectRepository.findById(id).orElse(null);
        if(project == null){
            throw new ProjectNotFoundExceptions("Project not found");
        }
        return project;
    }

    public List<Project> getProjectsByCompany(UUID companyId){
        return projectRepository.findByCompanyId(companyId);
    }

    @Transactional
    public Project updateProject(UUID projectId, Project updatedProject){
        Project existingProject = projectRepository.findById(projectId).orElse(null);
        if (existingProject == null) {
            throw new ProjectNotFoundExceptions("Project not found");
        }
        existingProject.setName(updatedProject.getName());
        return projectRepository.save(existingProject);
    }

    @Transactional
    public void deleteProject(UUID projectId){
        if(!projectRepository.existsById(projectId)){
            throw new ProjectNotFoundExceptions("Project not found");
        }
        projectRepository.deleteById(projectId);
    }
}
