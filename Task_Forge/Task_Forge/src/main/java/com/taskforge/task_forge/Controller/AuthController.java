package com.taskforge.task_forge.Controller;

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
    private AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user){
        try {
            return ResponseEntity.ok(authService.signup(user));
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password){
        try{
            return ResponseEntity.ok(authService.login(email, password));
        }catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(){
        return ResponseEntity.ok(authService.getProfile());
    }
}
