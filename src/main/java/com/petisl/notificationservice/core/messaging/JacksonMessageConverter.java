package com.candileasing.notificationservice.core.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 13/05/2021
 * Time: 7:18 PM
 */
public class JacksonMessageConverter extends Jackson2JsonMessageConverter {

    public JacksonMessageConverter() {
        super();
    }

    @Override
    public Object fromMessage(Message message) {
        message.getMessageProperties().setContentType("application/json");
        return super.fromMessage(message);
    }
}
