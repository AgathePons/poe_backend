package survey.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;



    public void sendMimeMessage(String toEmail, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        helper.setText(body, true); // Use this or above line.
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setFrom("fortimeShop1@gmail.com");
        mailSender.send(mimeMessage);
    }

    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(new InternetAddress("fortimeshop1@gmail.com"));

        try {
            message.setFrom(new InternetAddress("fortimeshop12@gmail.com", "JL - Aelion").toString());
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

        System.out.println("Mail sent successfully...");
    }

}
