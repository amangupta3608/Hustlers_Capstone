package com.taskforge.Task_Forge.Service;

import com.taskforge.Task_Forge.Exceptions.CompanyNotFoundExceptions;
import com.taskforge.Task_Forge.Model.Company;
import com.taskforge.Task_Forge.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company getCompanyById(UUID id){
        Company company = companyRepository.findById(id).orElse(null);
        if(company == null){
            throw new CompanyNotFoundExceptions("Company not found with id: " + id);
        }
        return company;
    }

    public Company createCompany(Company company){
        return companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(UUID id){
        if(!companyRepository.existsById(id)){
            throw new CompanyNotFoundExceptions("Company Not Found!");
        }
        companyRepository.deleteById(id);
    }
}
