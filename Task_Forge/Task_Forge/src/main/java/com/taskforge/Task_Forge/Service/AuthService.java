package com.taskforge.Task_Forge.Service;

import com.taskforge.Task_Forge.Exceptions.InvalidCredentialsExceptions;
import com.taskforge.Task_Forge.Exceptions.UserAlreadyExistsException;
import com.taskforge.Task_Forge.Model.User;
import com.taskforge.Task_Forge.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    public User signup(User user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistsException("Username already exists");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String login(String username, String password){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtUtils.generateJwtToken(authentication);
        }catch (BadCredentialsException e){
            throw new InvalidCredentialsExceptions("Invalid username or password")
        }
    }

    public Optional<User> getProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }
}
