package com.candileasing.notificationservice.config;

import com.candileasing.notificationservice.model.config.RabbitMqProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Project title: notification-service
 * Created by john.adeshola
 * Date: 25/02/2021
 * Time: 10:37 PM
 */
@Configuration
@EnableRabbit
@RequiredArgsConstructor
public class RabbitmqConfig {

    private final RabbitMqProperty rabbitMqProperty;

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(rabbitMqProperty.getEmailExchange(), true, false);
    }

    @Bean
    public Queue emailQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", rabbitMqProperty.getEmailExchange());
        args.put("x-dead-letter-routing-key", rabbitMqProperty.getEmailDeadQueue());
        args.put("x-message-ttl", 5000);
        return new Queue(rabbitMqProperty.getEmailQueue(), true, false, false, args);
    }

    @Bean
    public Queue emailDeadLetterQueue() {
        return new Queue(rabbitMqProperty.getEmailDeadQueue(), true, false, false);
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(emailExchange()).with(rabbitMqProperty.getRoutingKey());
    }

    @Bean
    public Binding emailBindingDeadLetter() {
        return BindingBuilder.bind(emailDeadLetterQueue()).to(emailExchange()).with(rabbitMqProperty.getEmailDeadQueue());
    }

    @Bean
    public DirectExchange pushExchange() {
        return new DirectExchange(rabbitMqProperty.getPushExchange(), true, false);
    }

    @Bean
    public Queue pushQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", rabbitMqProperty.getPushExchange());
        args.put("x-dead-letter-routing-key", rabbitMqProperty.getPushDeadQueue());
        args.put("x-message-ttl", 5000);
        return new Queue(rabbitMqProperty.getPushQueue(), true, false, false, args);
    }

    @Bean
    public Queue pushDeadLetterQueue() {
        return new Queue(rabbitMqProperty.getPushDeadQueue(), true, false, false);
    }

    @Bean
    public Binding pushBinding() {
        return BindingBuilder.bind(pushQueue()).to(pushExchange()).with(rabbitMqProperty.getRoutingKey());
    }

    @Bean
    public Binding pushBindingDeadLetter() {
        return BindingBuilder.bind(pushDeadLetterQueue()).to(pushExchange()).with(rabbitMqProperty.getPushDeadQueue());
    }

    @Bean
    public DirectExchange smsExchange() {
        return new DirectExchange(rabbitMqProperty.getPushExchange(), true, false);
    }

    @Bean
    public Queue smsQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", rabbitMqProperty.getSmsExchange());
        args.put("x-dead-letter-routing-key", rabbitMqProperty.getSmsDeadQueue());
        args.put("x-message-ttl", 5000);
        return new Queue(rabbitMqProperty.getSmsQueue(), true, false, false, args);
    }

    @Bean
    public Queue smsDeadLetterQueue() {
        return new Queue(rabbitMqProperty.getSmsDeadQueue(), true, false, false);
    }

    @Bean
    public Binding smsBinding() {
        return BindingBuilder.bind(smsQueue()).to(smsExchange()).with(rabbitMqProperty.getRoutingKey());
    }

    @Bean
    public Binding smsBindingDeadLetter() {
        return BindingBuilder.bind(smsDeadLetterQueue()).to(smsExchange()).with(rabbitMqProperty.getSmsDeadQueue());
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
