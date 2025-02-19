package com.taskforge.Task_Forge.Controller;

import com.taskforge.Task_Forge.Exceptions.ProjectNotFoundExceptions;
import com.taskforge.Task_Forge.Model.Project;
import com.taskforge.Task_Forge.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody Project project){
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id){
        try {
            return ResponseEntity.ok(projectService.getProject(id));
        }catch(ProjectNotFoundExceptions e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody Project project){
        try {
            return ResponseEntity.ok(projectService.updateProject(id, project));
        }catch (ProjectNotFoundExceptions e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id){
        try {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        }catch(ProjectNotFoundExceptions e){
            return ResponseEntity.noContent().build();
        }
    }
}
