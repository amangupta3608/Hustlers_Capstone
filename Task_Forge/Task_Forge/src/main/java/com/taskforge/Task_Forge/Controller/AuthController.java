package com.taskforge.Task_Forge.Controller;

import com.taskforge.Task_Forge.DTO.AuthRequest;
import com.taskforge.Task_Forge.DTO.AuthResponse;
import com.taskforge.Task_Forge.DTO.RegisterRequest;
import com.taskforge.Task_Forge.Exceptions.UserAlreadyExistsException;
import com.taskforge.Task_Forge.Exceptions.UserNotFoundException;
import com.taskforge.Task_Forge.Model.User;
import com.taskforge.Task_Forge.Service.AuthService;
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
