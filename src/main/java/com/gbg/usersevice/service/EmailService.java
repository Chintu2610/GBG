package com.gbg.usersevice.service;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.gbg.entity.Inquiry;
import com.gbg.usersevice.model.InquiryRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;
    //private final String salesEmail = "sales@growbeyondglobal.com";
    private final String salesEmail = "byamakeshpalei@gmail.com";
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

//    public void sendVerificationEmail(String toEmail, String token) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(fromEmail);
//        message.setTo(toEmail);
//        message.setSubject("Email Verification");
//        message.setText("Please verify your email by clicking the link: " +
//                "http://localhost:8080/verify?token=" + token);
//       // https://api.tradabulls.com/userService
//       // http://dev-api.tradabulls.com/userService
//        mailSender.send(message);
//    }
    
    public void sendVerificationEmail(String toEmail, String token, String userName) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("Email Verification");

            // Personalize the email body with the user's name
            String emailBody = "<html>" +
                    "<body>" +
                    "<p>Hello " + userName + ",</p>" +
                    "<p>Welcome to GBG!</p>" +
                    "<p>You are one step away</p>" +
                    "<p>Please verify your email by clicking the link below:</p>" +
                    "<a href='https://api.tradabulls.com/userService/verify?token=" + token + "'>Verify Email</a>" +
                    "<p>Best regards,<br/>" +
                    "Support Team,</p>" +
                    "GBG" +
                    "</body>" +
                    "</html>";

            helper.setText(emailBody, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Handle exception
            e.printStackTrace(); // Log or handle the exception as needed
        }
    }
    public void sendInquiryEmail(Inquiry inquiry) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(fromEmail);
            helper.setTo(salesEmail);
            helper.setSubject("New Inquiry Received - " + inquiry.getFullname());

            // Create a well-structured email body
            String emailBody = "<html><body>"
                    + "<h3>New Inquiry Details</h3>"
                    + "<p><strong>Full Name:</strong> " + inquiry.getFullname() + "</p>"
                    + "<p><strong>Mobile:</strong> " + inquiry.getMobile() + "</p>"
                    + "<p><strong>Email:</strong> " + inquiry.getEmail() + "</p>"
                    + "<p><strong>Commodity:</strong> " + inquiry.getCommodity() + "</p>"
                    + "<p><strong>Packing:</strong> " + inquiry.getPacking() + "</p>"
                    + "<p><strong>Delivery:</strong> " + inquiry.getDelivery() + "</p>"
                    + "<p><strong>Price:</strong> " + inquiry.getPrice() + "</p>"
                    + "<p><strong>Payment:</strong> " + inquiry.getPayment() + "</p>"
                    + "<p><strong>Quantity:</strong> " + inquiry.getQuantity() + "</p>"
                    + "<p><strong>Conditions:</strong> " + inquiry.getConditions() + "</p>"
                    + "<br/><p>Best Regards,</p>"
                    + "<p><strong>GBG Team</strong></p>"
                    + "</body></html>";

            helper.setText(emailBody, true);
            mailSender.send(mimeMessage);
            log.info("Inquiry email sent successfully to {}", salesEmail);
        } catch (MessagingException e) {
            log.error("Error sending inquiry email: {}", e.getMessage());
        }
    }
//cgf
}
