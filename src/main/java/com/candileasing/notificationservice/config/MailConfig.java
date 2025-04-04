package com.candileasing.notificationservice.config;

import com.candileasing.notificationservice.model.request.CustomEmailSetUp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Objects;
import java.util.Properties;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 8:53 PM
 */
@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender defaultJavaMailSender(Environment env) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(env.getProperty("spring.mail.host"));
        javaMailSender.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.mail.port"))));
        javaMailSender.setUsername(env.getProperty("spring.mail.username"));
        javaMailSender.setPassword(env.getProperty("spring.mail.password"));

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", Objects.requireNonNull(env.getProperty("spring.mail.properties.mail.smtp.protocol")));
        props.put("mail.smtp.auth", Objects.requireNonNull(env.getProperty("spring.mail.properties.mail.smtp.auth")));
        props.put("mail.smtp.starttls.enable", Objects.requireNonNull(env.getProperty("spring.mail.properties.mail.smtp.starttls.enable")));
        props.put("mail.smtp.starttls.required", Objects.requireNonNull(env.getProperty("spring.mail.properties.mail.smtp.starttls.required")));
        props.put("mail.debug", Objects.requireNonNull(env.getProperty("spring.mail.properties.mail.debug")));

        return javaMailSender;
    }

    public JavaMailSender runtimeJavaMailSender(CustomEmailSetUp customEmailSetUp) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(customEmailSetUp.getHost());
        javaMailSender.setPort(customEmailSetUp.getPort());
        javaMailSender.setUsername(customEmailSetUp.getUsername());
        javaMailSender.setPassword(customEmailSetUp.getPassword());

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", customEmailSetUp.getProtocol());
        props.put("mail.smtp.auth", customEmailSetUp.getSmtpAuth());
        props.put("mail.smtp.starttls.enable", customEmailSetUp.getStarttlsEnabled());
        props.put("mail.smtp.starttls.required", customEmailSetUp.getStarttlsRequired());
        props.put("mail.debug", true);

        return javaMailSender;
    }

    @Bean
    public SimpleMailMessage simpleMailMessage() {
        return new SimpleMailMessage();
    }

}
