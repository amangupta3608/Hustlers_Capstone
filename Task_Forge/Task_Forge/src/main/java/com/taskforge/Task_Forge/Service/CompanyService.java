package com.taskforge.Task_Forge.Service;

import com.taskforge.Task_Forge.DTO.CompanyRequest;
import com.taskforge.Task_Forge.Model.Company;
import com.taskforge.Task_Forge.Model.User;
import com.taskforge.Task_Forge.Repository.CompanyRepository;
import com.taskforge.Task_Forge.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

   public Company createCompany(CompanyRequest request){
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String email = auth.getName(); // Logged in User's email

       User user = userRepository.findByEmail(email)
               .orElseThrow(() -> new IllegalArgumentException("User not found"));

       if(companyRepository.findByName(request.getName()).isPresent()){
           throw new IllegalArgumentException("Company with this name already exist!");
       }

       Company company = new Company();
       company.setName(request.getName());
       company.setDescription(request.getDescription());
       company.setCreatedBy(user);
       company.setCreatedAt(LocalDateTime.now());

       return companyRepository.save(company);
   }
}
