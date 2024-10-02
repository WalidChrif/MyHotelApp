package com.myhotel.booking_management.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    public String exchange;
    @Value("${rabbitmq.queue.name}")
    public String queue;
    @Value("${rabbitmq.routing.key}")
    public String routingKey;

    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

//    @Bean
//    public MessageConverter converter(){
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(converter());
//        return rabbitTemplate;
//    }


}
