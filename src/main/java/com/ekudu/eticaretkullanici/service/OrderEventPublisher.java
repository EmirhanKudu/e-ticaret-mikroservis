package com.ekudu.eticaretkullanici.service;

import com.ekudu.eticaretkullanici.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderCreated(Long orderId) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_NOTIFICATION_QUEUE, orderId.toString());
    }
}
