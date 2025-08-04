package com.ekudu.eticaretkullanici.messaging;

import com.ekudu.eticaretkullanici.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserRegistrationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void publishNewUser(Long id, String username) {
        String message = id + ":" + username;
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_REG_QUEUE, message);
    }
}