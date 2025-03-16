package com.taskforge.task_forge.MailService;

import java.util.Random;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class CreateProjectMailService {
    private static final String EMAIL_FROM = "test123@gmail.com";
    private static final String EMAIL_PASSWORD = "eeja paja gsdj phef";

    public static void sendOTP(String recipientEmail, String otp) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("OTP for Project Creation");
            message.setText("Your OTP for project creation is: " + otp);

            Transport.send(message);
            System.out.println("OTP sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}

