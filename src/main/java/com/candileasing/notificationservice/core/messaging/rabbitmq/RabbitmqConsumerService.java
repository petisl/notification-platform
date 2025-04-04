package com.candileasing.notificationservice.core.messaging.rabbitmq;

import com.candileasing.notificationservice.core.utils.AppUtils;
import com.candileasing.notificationservice.model.request.MailRequest;
import com.candileasing.notificationservice.service.EmailService;
import com.candileasing.notificationservice.model.response.NotifyResponse;
import com.candileasing.notificationservice.service.PushNotifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitmqConsumerService {

    private final EmailService emailService;
    private final PushNotifyService pushNotifyService;

    @RabbitListener(queues = "alaje-email-notification")
    public void consumeEmail(MailRequest model) {
        log.info("Received message as generic: {}", AppUtils.toJson(model));
        emailService.sendEmail(model);
        log.info("received email notification");
    }

    @RabbitListener(queues = "alaje-push-notification")
    public void consumePushNotify(NotifyResponse model) {
        pushNotifyService.notifier(model);
        log.info("Received message as generic: {}", AppUtils.toJson(model));
        log.info("received push notification");
    }

}
