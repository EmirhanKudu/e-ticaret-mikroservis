package com.ekudu.eticaretkullanici.config;
import org.springframework.amqp.core.Queue;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String USER_REG_QUEUE = "user.registration.queue";


    @Bean
    public Queue userRegistrationQueue() {

        return new Queue(USER_REG_QUEUE, true);
    }
}
