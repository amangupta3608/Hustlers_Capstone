package com.taskforge.Task_Forge.Controller;

import com.taskforge.Task_Forge.Exceptions.CompanyNotFoundExceptions;
import com.taskforge.Task_Forge.Model.Company;
import com.taskforge.Task_Forge.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
        if(company.getName() == null || company.getName().trim().isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }
        Company createdCompany = companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable UUID id){
        try {
            Company company = companyService.getCompanyById(id);
            return ResponseEntity.ok(company);
        }catch (CompanyNotFoundExceptions e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((Company) Map.of("error", "Company not found", "id", id));
        }
    }
}
