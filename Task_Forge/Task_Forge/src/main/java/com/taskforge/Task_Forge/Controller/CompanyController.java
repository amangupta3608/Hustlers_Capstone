package com.taskforge.Task_Forge.Controller;

import com.taskforge.Task_Forge.DTO.CompanyRequest;
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
