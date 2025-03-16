package com.taskforge.task_forge.Controller;

import com.taskforge.task_forge.MailService.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // ✅ Send OTP
    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam String email) {
        emailService.sendOTP(email);
        return "OTP sent successfully!";
    }

    // ✅ Verify OTP
    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestParam String email, @RequestParam String otp) {
        if (emailService.verifyOTP(email, otp)) {
            return "OTP verified successfully!";
        } else {
            return "Invalid or expired OTP!";
        }
    }

    // ✅ Send Employee Invitation
    @PostMapping("/invite")
    public String sendInvite(@RequestParam String email, @RequestParam String organization) {
        emailService.sendEmployeeInvite(email, organization);
        return "Invitation sent successfully!";
    }

    // ✅ Validate Invitation Link
    @GetMapping("/validate-invite")
    public String validateInvitation(@RequestParam String token) {
        if (emailService.validateInvitation(token)) {
            return "Invitation is valid!";
        } else {
            return "Invalid or expired invitation!";
        }
    }
}
