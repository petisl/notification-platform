package com.candileasing.notificationservice.service.impl;

import com.candileasing.notificationservice.core.constants.MailConstant;
import com.candileasing.notificationservice.core.exceptions.CustomException;
import com.candileasing.notificationservice.core.messaging.rabbitmq.RabbitmqProducerService;
import com.candileasing.notificationservice.model.request.CustomEmailSetUp;
import com.candileasing.notificationservice.model.request.MailRequest;
import com.candileasing.notificationservice.model.response.PaginateResponse;
import com.candileasing.notificationservice.config.MailConfig;
import com.candileasing.notificationservice.persistence.entity.EmailSetUp;
import com.candileasing.notificationservice.persistence.repository.EmailSetUpRepository;
import com.candileasing.notificationservice.service.EmailSetUpService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailSetUpServiceImpl implements EmailSetUpService {

    private final Environment env;
    private final EmailSetUpRepository setUpRepository;
    private final RabbitmqProducerService producerService;

    @Override
    public EmailSetUp createEmailSetup(CustomEmailSetUp request) {

        setUpRepository.findByUsernameAndOrganization(request.getUsername(), request.getOrganization()).ifPresent(data -> {
            if (data != null) {
                throw new CustomException("Email has been previously configured");
            }
        });
        return setUpRepository.save(EmailSetUp.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .host(request.getHost())
                .port(request.getPort())
                .protocol(request.getProtocol())
                .smtpAuth(request.getSmtpAuth())
                .starttlsEnabled(request.getStarttlsEnabled())
                .starttlsRequired(request.getStarttlsRequired())
                .primarySender(request.getPrimarySender())
                .secondarySender(request.getSecondarySender())
                .organization(request.getOrganization())
                .build());
    }

    @Override
    public EmailSetUp updateEmailSetup(CustomEmailSetUp request) {

        setUpRepository.findByUsernameAndOrganization(request.getUsername(), request.getOrganization()).map(data -> {
            if (data == null) {
                throw new CustomException("Email setup cannot be found on the system");
            }
            if (!StringUtils.isBlank(request.getUsername())) {
                data.setUsername(request.getUsername());
            }
            if (!StringUtils.isBlank(request.getPassword())) {
                data.setPassword(request.getPassword());
            }
            if (!StringUtils.isBlank(request.getHost())) {
                data.setHost(request.getHost());
            }
            if (Objects.nonNull(request.getPort())) {
                data.setPort(request.getPort());
            }
            if (Objects.nonNull(request.getProtocol())) {
                data.setProtocol(request.getProtocol());
            }
            if (Objects.nonNull(request.getStarttlsEnabled())) {
                data.setStarttlsEnabled(request.getStarttlsEnabled());
            }
            if (Objects.nonNull(request.getStarttlsRequired())) {
                data.setStarttlsRequired(request.getStarttlsRequired());
            }
            if (Objects.nonNull(request.getSmtpAuth())) {
                data.setSmtpAuth(request.getSmtpAuth());
            }
            if (Objects.nonNull(request.getPrimarySender())) {
                data.setPrimarySender(request.getPrimarySender());
            }
            if (Objects.nonNull(request.getSecondarySender())) {
                data.setSecondarySender(request.getSecondarySender());
            }
            return setUpRepository.save(data);
        });
        return null;
    }

    @Override
    public EmailSetUp fetchEmailSetup(Long organization) {
        return setUpRepository.findByOrganization(organization).orElseThrow(() -> new CustomException("Email setup cannot be found"));
    }

    @Override
    public PaginateResponse<EmailSetUp> fetchEmailSetup(int start, int limit, String search) {
        return null;
    }

    @Override
    public JavaMailSender runtimeJavaMailService() {
        MailConfig mailConfig = new MailConfig();
        return mailConfig.defaultJavaMailSender(env);
    }

    @Override
    public Boolean testEmail(String templateName, String[] recipients, Long orgId) {
        producerService.sendEmail(MailRequest.builder()
                .from("noreply@c-ileasing.com")
                .to(recipients)
                .subject(templateName.toLowerCase().replaceAll("_", " "))
                .useTemplate(true)
                .templateName(templateName.toLowerCase())
                .type(MailConstant.EmailTemplate.HTML)
                .build());
        return true;
    }

    @Override
    public Boolean sendEmailNotification(String[] to, String templateName) {

        producerService.sendEmail(MailRequest.builder()
                .from("noreply@c-ileasing.com")
                .to(to)
                .subject(templateName.toLowerCase().replaceAll("_", " "))
                .useTemplate(true)
                .messageMap(new HashMap<>())
                .templateName(templateName.toLowerCase())
                .type(MailConstant.EmailTemplate.HTML)
                .build());
        return true;
    }
}
