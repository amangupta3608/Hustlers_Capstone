package com.taskforge.task_forge.Controller;

import com.taskforge.task_forge.DTO.AuthRequest;
import com.taskforge.task_forge.DTO.AuthResponse;
import com.taskforge.task_forge.DTO.RegisterRequest;
import com.taskforge.task_forge.Exceptions.UserAlreadyExistsException;
import com.taskforge.task_forge.Exceptions.UserNotFoundException;
import com.taskforge.task_forge.Model.User;
import com.taskforge.task_forge.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.login(authRequest));
    }

}