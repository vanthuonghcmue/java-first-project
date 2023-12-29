package com.example.demo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

@SpringBootTest
public class SendMailTests {
//    @Test
//    void testSendMail() throws UnsupportedEncodingException, MessagingException {
//        JavaMailSenderImpl sender = new JavaMailSenderImpl();
//        sender.setHost("localhost");
//        sender.setPort(1025);
//
//        MimeMessage message = sender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        helper.setFrom(new InternetAddress("datmt@local.com", "Datmt"));
//        helper.setSubject("Hello, world!");
//        helper.setTo("test@mail.com");
//        helper.setText("Thank you for ordering!");
//
//        sender.send(message);
//    }
}
