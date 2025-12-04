package com.candileasing.notificationservice.service;

import com.candileasing.notificationservice.model.request.CustomEmailSetUp;
import com.candileasing.notificationservice.model.response.PaginateResponse;
import com.candileasing.notificationservice.persistence.entity.EmailSetUp;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 9:48 PM
 */
public interface EmailSetUpService {

    public EmailSetUp createEmailSetup(CustomEmailSetUp setUp);

    public EmailSetUp updateEmailSetup(CustomEmailSetUp setUp);

    public EmailSetUp fetchEmailSetup(Long organization);

    public PaginateResponse<EmailSetUp> fetchEmailSetup(int start, int limit, String search);

    public JavaMailSender runtimeJavaMailService();

    public Boolean testEmail(String templateName, String[] recipients, Long orgId);

    public Boolean sendEmailNotification(String[] to, String templateName);

}
