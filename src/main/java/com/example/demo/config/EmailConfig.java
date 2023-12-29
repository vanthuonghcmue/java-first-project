package com.example.demo.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

//@Getter
//@Setter
//@Configuration
//@Validated
//@ConfigurationProperties(prefix = "spring.mail")
//public class EmailConfig {
//    @NotNull
//    private String host;
//
//    @NotNull
//    private int port;
//
//    @Bean
//    public JavaMailSenderImpl mailer() {
//        JavaMailSenderImpl sender = new JavaMailSenderImpl();
//        sender.setHost(host);
//        sender.setPort(port);
//        return sender;
//    }
//
//
//}
