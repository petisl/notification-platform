package com.candileasing.notificationservice.service;

import com.candileasing.notificationservice.model.request.MailRequest;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 8:51 PM
 */
public interface EmailService {

    void sendEmail(MailRequest mailRequest);

}
