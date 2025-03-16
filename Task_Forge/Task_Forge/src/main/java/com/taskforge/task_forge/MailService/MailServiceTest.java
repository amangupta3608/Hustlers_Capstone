package com.taskforge.task_forge.MailService;


public class MailServiceTest {
    public static void main(String[] args) {
        String recipient = "test123@gmail.com"; // Change this to a real email
        String otp = RegisterMailService.generateOTP();

        RegisterMailService.sendOTP(recipient, otp);
        AuthenticateMailService.sendOTP(recipient, otp);
        CreateCompanyMailService.sendOTP(recipient, otp);
        CreateProjectMailService.sendOTP(recipient, otp);

        System.out.println("Emails Sent Successfully!");
    }
}
