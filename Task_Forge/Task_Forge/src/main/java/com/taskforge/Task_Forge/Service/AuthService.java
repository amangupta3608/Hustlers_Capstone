package com.taskforge.Task_Forge.Service;

import com.taskforge.Task_Forge.Exceptions.InvalidCredentialsException; // Fixed Exception Name
import com.taskforge.Task_Forge.Exceptions.UserAlreadyExistsException;
import com.taskforge.Task_Forge.Model.User;
import com.taskforge.Task_Forge.Repository.UserRepository;
import com.taskforge.Task_Forge.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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
