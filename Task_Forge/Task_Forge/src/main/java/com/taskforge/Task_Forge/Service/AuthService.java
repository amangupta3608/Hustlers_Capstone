package com.taskforge.Task_Forge.Service;

import com.taskforge.Task_Forge.DTO.AuthRequest;
import com.taskforge.Task_Forge.DTO.AuthResponse;
import com.taskforge.Task_Forge.DTO.RegisterRequest;
import com.taskforge.Task_Forge.Model.User;
import com.taskforge.Task_Forge.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private com.example.agiletool.util.JwtUtil jwtUtils;

    public String register(RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setUsername(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
        return "User registered successfully";
    }


    public AuthResponse login(AuthRequest request){
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if(userOptional.isPresent()){
            User user = userOptional.get();

            if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
                String token = jwtUtils.generateToken(user.getEmail());
                return new AuthResponse();
            }
        }

        throw new RuntimeException("Invalid email or password");
    }


}
