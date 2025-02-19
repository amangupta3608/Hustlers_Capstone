package com.taskforge.Task_Forge.Controller;

import com.taskforge.Task_Forge.Exceptions.CompanyNotFoundExceptions;
import com.taskforge.Task_Forge.Model.Company;
import com.taskforge.Task_Forge.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getCompany(@PathVariable Long id){
        try {
            return ResponseEntity.ok(companyService.getCompay(id));
        }catch (CompanyNotFoundExceptions e){
            return ResponseEntity.notFound().build();
        }
    }
}
