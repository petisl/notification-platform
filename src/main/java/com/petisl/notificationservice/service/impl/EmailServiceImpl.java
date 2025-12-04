package com.candileasing.notificationservice.service.impl;

import com.candileasing.notificationservice.model.request.MailRequest;
import com.candileasing.notificationservice.service.EmailService;
import com.candileasing.notificationservice.service.EmailSetUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.util.Date;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final SpringTemplateEngine templateEngine;
    private final SimpleMailMessage simpleMail;
    private final EmailSetUpService javaMailSender;

    @Override
    public void sendEmail(MailRequest mailRequest) {
        if (mailRequest.isHasAttachment()) {
            sendMailWithAttachment(mailRequest);
        } else if (mailRequest.isUseTemplate()) {
            sendHtmlEmail(mailRequest);
        } else {
            sendPlainEmail(mailRequest);
        }
    }

    private void sendPlainEmail(MailRequest model) {
        try {
            simpleMail.setFrom(model.getFrom());
            simpleMail.setTo(model.getTo());
            simpleMail.setBcc(model.getBcc());
            simpleMail.setCc(model.getCc());
            simpleMail.setSubject(model.getSubject());
            if (model.isUseTemplate()) {
                Context context = new Context(model.getLocale(), model.getMessageMap());
                String htmlContext = templateEngine.process(model.getTemplateName(), context);
                simpleMail.setText(htmlContext);
            } else {
                simpleMail.setText(model.getMessage());
            }
            simpleMail.setSentDate(new Date());
            javaMailSender.runtimeJavaMailService().send(simpleMail);
            log.info("Plain Email Sent !");
        } catch (TemplateProcessingException e) {
            log.error("Error occurred sending mail with plain message:: {}", e.getMessage());
        }
    }

    private void sendHtmlEmail(MailRequest model) {
        try {
            final MimeMessage mime = javaMailSender.runtimeJavaMailService().createMimeMessage();
            final MimeMessageHelper messageHelper = new MimeMessageHelper(mime, true, "UTF-8");
            messageHelper.setFrom(model.getFrom());
            messageHelper.setSubject(model.getSubject());
            messageHelper.setTo(model.getTo());
            messageHelper.setBcc(model.getBcc());
            messageHelper.setCc(model.getCc());
            Context context = new Context(model.getLocale(), model.getMessageMap());
            String htmlContext = templateEngine.process(model.getTemplateName(), context);
            messageHelper.setText(htmlContext, true);
            javaMailSender.runtimeJavaMailService().send(mime);
            log.info("Html Email Sent !");
        } catch (MessagingException | TemplateProcessingException e) {
            log.error("Error occurred sending mail with HTML message:: {}", e.getMessage());
        }
    }

    private void sendMailWithAttachment(MailRequest mailRequest) {

        try {
            final MimeMessage mimeMessage = javaMailSender.runtimeJavaMailService().createMimeMessage();
            final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom(mailRequest.getFrom());
            messageHelper.setSubject(mailRequest.getSubject());
            messageHelper.setTo(mailRequest.getTo());
            messageHelper.setBcc(mailRequest.getBcc());
            messageHelper.setCc(mailRequest.getCc());

            // Create the HTML body using Thymeleaf
            Context context = new Context(mailRequest.getLocale(), mailRequest.getMessageMap());
            String htmlContext = templateEngine.process(mailRequest.getTemplateName(), context);
            messageHelper.setText(htmlContext, true);

            if (mailRequest.getHasUrlLocation().equals(Boolean.TRUE)) {
                URL url = new URL(mailRequest.getUrl());
                UrlResource urlResource = new UrlResource(url);
                messageHelper.addAttachment(mailRequest.getAttachmentFileName(), urlResource, mailRequest.getAttachmentContentType());
            } else {
                final InputStreamSource attachmentSource = new ByteArrayResource(mailRequest.getAttachmentBytes(), mailRequest.getDescription());
                messageHelper.addAttachment(mailRequest.getAttachmentFileName(), attachmentSource, mailRequest.getAttachmentContentType());
            }
            javaMailSender.runtimeJavaMailService().send(mimeMessage);
        } catch (MessagingException | TemplateProcessingException | IOException e) {
            log.error("Error occurred sending mail with attachment:: {}", e.getMessage());
        }
    }


}
