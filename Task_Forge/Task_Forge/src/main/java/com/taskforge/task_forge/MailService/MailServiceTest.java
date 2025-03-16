package com.taskforge.task_forge.MailService;

import com.taskforge.task_forge.MailService.RegisterMailService;
import com.taskforge.task_forge.MailService.AuthenticateMailService;
import com.taskforge.task_forge.MailService.CreateCompanyMailService;
import com.taskforge.task_forge.MailService.CreateProjectMailService;


public class MailServiceTest {
    public static void main(String[] args) {
        String recipient = "vanshikavs3008@gmail.com"; // Change this to a real email
        String otp = RegisterMailService.generateOTP();

        RegisterMailService.sendOTP(recipient, otp);
        AuthenticateMailService.sendOTP(recipient, otp);
        CreateCompanyMailService.sendOTP(recipient, otp);
        CreateProjectMailService.sendOTP(recipient, otp);

        System.out.println("Emails Sent Successfully!");
    }
}
