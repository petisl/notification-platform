package com.candileasing.notificationservice.model.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.io.Serializable;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 10:31 PM
 */
@ConstructorBinding
@ConfigurationProperties("rabbit.queue")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMqProperty implements Serializable {

    private String emailQueue;
    private String emailDeadQueue;
    private String emailExchange;

    private String pushQueue;
    private String pushDeadQueue;
    private String pushExchange;

    private String smsQueue;
    private String smsDeadQueue;
    private String smsExchange;

    private String routingKey;
    private String wsTopic;
}
