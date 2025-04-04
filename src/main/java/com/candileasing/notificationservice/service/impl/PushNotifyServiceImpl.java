package com.candileasing.notificationservice.service.impl;

import com.candileasing.notificationservice.model.response.NotifyResponse;
import com.candileasing.notificationservice.service.PushNotifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PushNotifyServiceImpl implements PushNotifyService {

    private final SimpMessagingTemplate messagingTemplate;

    @Value("${ws.notification}")
    private String destination;

    @Override
    public void notifier(NotifyResponse model) {
//        log.info("Sending notification to {}", AppUtils.getAuthUser().get().getUsername());
        messagingTemplate.convertAndSendToUser("timadeshola", destination, model);
        log.info("notification send successfully. Object:: {}", model.toString());
    }


}
