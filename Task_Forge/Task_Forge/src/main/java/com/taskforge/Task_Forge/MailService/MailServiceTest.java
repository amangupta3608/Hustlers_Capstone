package com.taskforge.Task_Forge.MailService;

import com.taskforge.Task_Forge.MailService.RegisterMailService;
import com.taskforge.Task_Forge.MailService.AuthenticateMailService;
import com.taskforge.Task_Forge.MailService.CreateCompanyMailService;
import com.taskforge.Task_Forge.MailService.CreateProjectMailService;


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
