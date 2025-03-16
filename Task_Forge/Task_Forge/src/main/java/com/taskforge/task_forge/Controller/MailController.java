package com.taskforge.task_forge.Controller;

import com.taskforge.task_forge.MailService.AuthenticateMailService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class MailController {

    @PostMapping("/sendOTP")
    public Map<String, String> sendOTP(@RequestParam String email) {
        String otp = AuthenticateMailService.generateOTP(); // Generate OTP
        AuthenticateMailService.sendOTP(email, otp); // Send OTP

        Map<String, String> response = new HashMap<>();
        response.put("message", "OTP sent successfully to " + email);
        response.put("otp", otp);
        return response;
    }
}

