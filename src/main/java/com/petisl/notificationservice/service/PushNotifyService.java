package com.candileasing.notificationservice.service;

import com.candileasing.notificationservice.model.response.NotifyResponse;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 10:59 PM
 */
public interface PushNotifyService {

    public void notifier(NotifyResponse model);
}
