package com.ekudu.eticaretkullanici.messaging;

import com.ekudu.eticaretkullanici.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationConsumer {

    @RabbitListener(queues = RabbitMQConfig.USER_REG_QUEUE)
    public void handleNewUser(String message) {

        System.out.println("RabbitMQ'den yeni kullanıcı mesajı alındı: " + message);

    }
}
