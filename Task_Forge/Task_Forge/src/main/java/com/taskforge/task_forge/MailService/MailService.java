package com.taskforge.task_forge.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendInviteEmail(String toEmail, String organizationName, String inviteLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("You're Invited to Join " + organizationName);
        message.setText("Hello,\n\n" +
                "You have been invited to join " + organizationName + ".\n" +
                "Click the link below to accept the invitation:\n\n" +
                inviteLink + "\n\n" +
                "Best regards,\n" +
                organizationName + " Team");

        mailSender.send(message);
    }
}
