package com.DTO.Relationship.Service.Utility;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) {
        //simple mail message which contains the body of email structure.
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);

    }

    public void sendStanderdEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        javaMailSender.send(message);
    }

    public String greetingEmailHtml(String userName) {
        String html = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }" +
                ".email-container { max-width: 600px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 10px; }" +
                ".header { background-color: #007bff; color: white; padding: 15px; text-align: center; font-size: 24px; font-weight: bold; }" +
                ".content { padding: 20px; text-align: center; font-size: 18px; color: #333; }" +
                ".footer { text-align: center; padding: 15px; font-size: 14px; color: #777; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='email-container'>" +
                "<div class='header'>Welcome to Our Platform!</div>" +
                "<div class='content'>" +
                "<p>Dear " + userName + ",</p>" +
                "<p>We’re thrilled to have you on board! 🎉</p>" +
                "<p>Start exploring and enjoy your journey with us.</p>" +
                "<p>Feel free to reach out if you have any questions.</p>" +
                "<br>" +
                "<p>Best regards,</p>" +
                "<p><strong>Signimus technlogy pvt ltd</strong></p>" +
                "</div>" +
                "<div class='footer'>© 2025 Our Platform. All rights reserved.</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        return html;
    }
    //cron = "sec min hr day month week"
    @Scheduled(cron = "0 55 12 * * ?")
    public void report() throws MessagingException {
        String to = "ankitsharma.as420@gmail.com";
        String subject = "test email for Scheduling";
        String text = greetingEmailHtml("Ankit sharma");
        sendStanderdEmail(to,subject,text);
    }
}

// to, subject, text
