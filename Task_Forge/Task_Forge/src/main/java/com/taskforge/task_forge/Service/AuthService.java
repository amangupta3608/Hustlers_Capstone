package com.taskforge.task_forge.Service;

import com.taskforge.task_forge.Exceptions.InvalidCredentialsException; // Fixed Exception Name
import com.taskforge.task_forge.Model.User;
import com.taskforge.task_forge.Repository.UserRepository;
import com.taskforge.task_forge.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User Registered Successfully";
    }

    public String login(String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent() && passwordEncoder.matches(password, existingUser.get().getPassword())){
            return jwtUtils.generateToken(existingUser.get().getId(), email);
        }
        throw new InvalidCredentialsException("Invalid Credentials");
    }

    public Optional<User> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();//
        }

        String username = authentication.getName();
        return userRepository.findByEmail(username);
    }
}
