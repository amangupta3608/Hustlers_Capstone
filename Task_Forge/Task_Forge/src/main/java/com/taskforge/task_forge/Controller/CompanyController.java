package com.taskforge.task_forge.Controller;

import com.taskforge.task_forge.Exceptions.CompanyNotFoundExceptions;
import com.taskforge.task_forge.Model.Company;
import com.taskforge.task_forge.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody Company company){
        return ResponseEntity.ok(companyService.createCompany(company));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@PathVariable UUID id){
        try {
            return ResponseEntity.ok(companyService.getCompanyById(id));
        }catch (CompanyNotFoundExceptions e){
            return ResponseEntity.notFound().build();
        }
    }
}
