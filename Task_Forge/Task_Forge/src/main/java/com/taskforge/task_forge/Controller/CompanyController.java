package com.taskforge.task_forge.Controller;

import com.taskforge.task_forge.DTO.CompanyRequest;
import com.taskforge.task_forge.Exceptions.CompanyNotFoundExceptions;
import com.taskforge.task_forge.Model.Company;
import com.taskforge.task_forge.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    public  CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @PostMapping("/create")
    public ResponseEntity<Company> createCompany(@RequestBody CompanyRequest request){
        return ResponseEntity.ok(companyService.createCompany(request));
    }
}
