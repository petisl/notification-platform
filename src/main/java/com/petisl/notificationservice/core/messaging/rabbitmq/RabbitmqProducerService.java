package com.candileasing.notificationservice.core.messaging.rabbitmq;

import com.candileasing.notificationservice.model.config.RabbitMqProperty;
import com.candileasing.notificationservice.model.request.MailRequest;
import com.candileasing.notificationservice.model.response.NotifyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitmqProducerService {

    private final AmqpTemplate rabbitTemplate;
    private final RabbitMqProperty rabbitMqProperty;

    public void sendEmail(MailRequest model) {
        log.info("Sending email message...");
        log.info("exchange property: {} :{}", rabbitMqProperty.getEmailExchange(), rabbitMqProperty.getRoutingKey());
        rabbitTemplate.convertAndSend(rabbitMqProperty.getEmailExchange(), rabbitMqProperty.getRoutingKey(), model);
        log.info("email notification sent!!");
    }

    public void sendPushNotification(NotifyResponse model) {
        log.info("Sending push message...");
        rabbitTemplate.convertAndSend(rabbitMqProperty.getPushExchange(), rabbitMqProperty.getRoutingKey(), model);
        log.info("push notification sent!!");
    }
}
