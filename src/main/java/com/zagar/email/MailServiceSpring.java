package com.zagar.email;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by naayadaa on 01.08.16.
 */
public class MailServiceSpring{

    private JavaMailSenderImpl mailSender;

    public MailServiceSpring(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String subject, String text, String toEmail){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setSubject(subject);
            helper.setText(text);
            helper.setTo(new InternetAddress(toEmail));

            mailSender.send(message);

        }catch(MessagingException e){
            throw new RuntimeException(e);
        }


    }
}
