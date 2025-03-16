package com.taskforge.task_forge.Controller;

import com.taskforge.task_forge.Exceptions.ProjectNotFoundExceptions;
import com.taskforge.task_forge.Model.Project;
import com.taskforge.task_forge.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestParam UUID companyId, @RequestBody Project project){
        return ResponseEntity.ok(projectService.createProject(companyId, project));
    }

    @GetMapping
    public ResponseEntity<Project> getAllProjects(){
        return ResponseEntity.ok((Project) projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Project>> getGetProjectById(@PathVariable UUID id){
        try {
            return ResponseEntity.ok(projectService.getProjectById(id));
        }catch(ProjectNotFoundExceptions e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable UUID id, @RequestBody Project project){
        try {
            return ResponseEntity.ok(projectService.updateProject(id, project));
        }catch (ProjectNotFoundExceptions e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable UUID id){
        try {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        }catch(ProjectNotFoundExceptions e){
            return ResponseEntity.noContent().build();
        }
    }
}
