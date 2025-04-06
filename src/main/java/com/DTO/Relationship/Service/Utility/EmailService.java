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
        String html = "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: 'Arial', sans-serif; background-color: #f4f4f4; padding: 40px; text-align: center; }"
                + ".container { max-width: 600px; margin: auto; background: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0px 4px 8px rgba(0,0,0,0.2); }"
                + ".logo { margin-bottom: 20px; width: 500px; height: 100px}"
                + "h1 { color: #4CAF50; font-size: 24px; margin-bottom: 10px; }"
                + "p { color: #555; font-size: 16px; margin-bottom: 20px; }"
                + ".highlight { color: #ff9800; font-weight: bold; }"
                + ".cta-btn { background: #4CAF50; color: white; padding: 12px 24px; text-decoration: none; font-size: 18px; border-radius: 5px; display: inline-block; margin-top: 20px; }"
                + ".footer { margin-top: 30px; font-size: 12px; color: #777; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<img src='https://media.licdn.com/dms/image/v2/D4D1BAQG9EbWRESmg0Q/company-background_10000/company-background_10000/0/1655539877313/signi_mus_cover?e=2147483647&v=beta&t=2BTrmg0FzqqdERHianLRFRnXTY_x6UediPxkL28ngkA' class='logo' width='120' alt='Signimus Technology Pvt Ltd'/>"
                + "<h1>Welcome to <span class='highlight'>Signimus Technology Pvt Ltd</span>, " + userName + "!</h1>"
                + "<p>We are excited to have you onboard. Our team is here to innovate, inspire, and create technology solutions that empower businesses and individuals.</p>"
                + "<p>Let's build something amazing together!</p>"
                + "<a href='https://signimus.com' class='cta-btn'>Explore More</a>"
                + "<div class='footer'>"
                + "<p>&copy; 2025 Signimus Technology Pvt Ltd. All Rights Reserved.</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

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
