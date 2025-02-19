package com.taskforge.Task_Forge.Service;

import com.taskforge.Task_Forge.Exceptions.CompanyNotFoundExceptions;
import com.taskforge.Task_Forge.Model.Company;
import com.taskforge.Task_Forge.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public Company createCompany(Company company){
        return companyRepository.save(company);
    }

    public Company getCompay(Long id){
        return companyRepository.findById(id).orElseThrow(()-> new CompanyNotFoundExceptions("Company Not Found"));
    }
}
