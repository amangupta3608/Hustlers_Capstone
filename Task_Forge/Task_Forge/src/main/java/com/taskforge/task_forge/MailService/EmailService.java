package com.taskforge.task_forge.MailService;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final Map<String, OTPDetails> otpStorage = new HashMap<>();
    private final Map<String, InvitationDetails> invitationStorage = new HashMap<>();

    @Value("${spring.mail.username}")
    private String senderEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // ✅ Send OTP for Verification (expires in 5 minutes)
    public void sendOTP(String recipientEmail) {
        String otp = generateOTP();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

        otpStorage.put(recipientEmail, new OTPDetails(otp, expiryTime));

        String subject = "Your OTP Code";
        String body = "Your OTP for verification is: <b>" + otp + "</b>. It expires in 5 minutes.";

        sendEmail(recipientEmail, subject, body);
    }

    // ✅ Verify OTP
    public boolean verifyOTP(String email, String enteredOTP) {
        OTPDetails details = otpStorage.get(email);
        if (details == null || LocalDateTime.now().isAfter(details.getExpiryTime())) {
            return false; // OTP expired or not found
        }
        return details.getOtp().equals(enteredOTP);
    }

    // ✅ Send Employee Invitation (Valid for 24 Hours)
    public void sendEmployeeInvite(String recipientEmail, String organizationName) {
        String invitationToken = generateOTP();  // Reusing OTP generator for simplicity
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(24);

        invitationStorage.put(recipientEmail, new InvitationDetails(invitationToken, expiryTime));

        String inviteLink = "https://yourapp.com/invite?token=" + invitationToken;
        String subject = "Invitation to Join " + organizationName;
        String body = "You have been invited to join <b>" + organizationName + "</b>.<br>"
                + "Click the link below to accept the invitation:<br>"
                + "<a href='" + inviteLink + "'>Accept Invitation</a><br>"
                + "This invitation is valid for 24 hours.";

        sendEmail(recipientEmail, subject, body);
    }

    // ✅ Validate Invitation Link
    public boolean validateInvitation(String token) {
        for (Map.Entry<String, InvitationDetails> entry : invitationStorage.entrySet()) {
            InvitationDetails details = entry.getValue();
            if (details.getToken().equals(token) && LocalDateTime.now().isBefore(details.getExpiryTime())) {
                return true; // Invitation is valid
            }
        }
        return false; // Invalid or expired token
    }

    // ✅ Send Email
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(senderEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // Enable HTML formatting

            mailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error occurred while sending email", e);
        }
    }

    // ✅ Generate Random 6-digit OTP
    private String generateOTP() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    // ✅ OTP Details Class
    @Getter
    private static class OTPDetails {
        private final String otp;
        private final LocalDateTime expiryTime;

        public OTPDetails(String otp, LocalDateTime expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }
    }

    // ✅ Invitation Details Class
    @Getter
    private static class InvitationDetails {
        private final String token;
        private final LocalDateTime expiryTime;

        public InvitationDetails(String token, LocalDateTime expiryTime) {
            this.token = token;
            this.expiryTime = expiryTime;
        }
    }
}
