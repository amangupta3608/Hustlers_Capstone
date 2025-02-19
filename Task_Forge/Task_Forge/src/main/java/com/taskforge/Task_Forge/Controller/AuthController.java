package com.taskforge.Task_Forge.Controller;

import com.taskforge.Task_Forge.Exceptions.InvalidCredentialsExceptions;
import com.taskforge.Task_Forge.Exceptions.UserAlreadyExistsException;
import com.taskforge.Task_Forge.Model.User;
import com.taskforge.Task_Forge.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> singup(@RequestBody User user){
        try {
            return ResponseEntity.ok(authService.signup(user));
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            String jwt = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(new JwtReponse(jwt));
        }catch(InvalidCredentialsExceptions e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(){
        return ResponseEntity.ok(authService.getProfile());
    }
}
