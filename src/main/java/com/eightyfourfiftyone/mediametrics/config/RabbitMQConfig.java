package com.eightyfourfiftyone.mediametrics.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_JSON_PAYLOAD = "json-payload-queue";
    public static final String EXCHANGE_ORDERS = "json-payload-exchange";

    @Value("")
    String queueName;

    @Value("")
    String exchange;

    @Value("")
    private String routingkey;

    @Bean
    Queue queue() {

        //return new Queue(queueName, false);
        return QueueBuilder.durable(QUEUE_JSON_PAYLOAD).build();
    }


    @Bean
    Exchange exchange() {

        //return new DirectExchange(exchange);
        return ExchangeBuilder.topicExchange(EXCHANGE_ORDERS).build();
    }

    /*@Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }*/

    @Bean
    Binding binding(Queue ordersQueue, TopicExchange ordersExchange) {
        return BindingBuilder.bind(ordersQueue).to(ordersExchange).with(QUEUE_JSON_PAYLOAD);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /*@Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate1 = new RabbitTemplate(connectionFactory);
        rabbitTemplate1.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate1;
    }*/


}
