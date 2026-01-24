package ds.assignment_2024.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ds.assignment_2024.entities.AdoptionRequest;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired(required = false)
    private JavaMailSender javaMailSender;

    public void sendAdoptionApprovalEmail(AdoptionRequest request) {
        if (javaMailSender == null) {
            logger.warn("Mail sender not configured. Email would be sent to: {}", request.getEmail());
            return;
        }

        String subject = "Adoption Request Approved - " + request.getAnimal().getName();
        String body = "Dear " + request.getName() + ",\n\n" +
                "Congratulations! Your adoption request for " + request.getAnimal().getName() + " has been APPROVED.\n\n" +
                "The animal shelter will contact you shortly to complete the adoption process.\n\n" +
                "Thank you for choosing to adopt!\n\n" +
                "Best regards,\n" +
                "Animal Shelter Team";

        logger.info("Sending approval email to: {}", request.getEmail());
        sendEmail(request.getEmail(), subject, body);
    }

    public void sendAdoptionRejectionEmail(AdoptionRequest request) {
        if (javaMailSender == null) {
            logger.warn("Mail sender not configured. Email would be sent to: {}", request.getEmail());
            return;
        }

        String subject = "Adoption Request Status - " + request.getAnimal().getName();
        String body = "Dear " + request.getName() + ",\n\n" +
                "Thank you for your interest in adopting " + request.getAnimal().getName() + ".\n\n" +
                "Unfortunately, your adoption request has been REJECTED at this time.\n\n" +
                "We encourage you to check our other available animals or try again in the future.\n\n" +
                "Best regards,\n" +
                "Animal Shelter Team";

        logger.info("Sending rejection email to: {}", request.getEmail());
        sendEmail(request.getEmail(), subject, body);
    }

    private void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("aadoptioncenter586@gmail.com");

            javaMailSender.send(message);
            logger.info("Email sent successfully to: {}", to);
        } catch (Exception e) {
            logger.error("Error sending email to {}: {}", to, e.getMessage(), e);
        }
    }
}