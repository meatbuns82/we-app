package com.luwh.we.app.message.config;

import com.luwh.we.app.message.callback.ProducerCallback;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author lu.wh
 * @date 2023/10/07 17/23/59
 * @description
 */
@Configuration
public class RabbitMqConfig {

    @Resource
    private ProducerCallback callback;
//    @Resource
//    private RabbitTemplate rabbitTemplate;
//
//
//    public void initQueue(){
//        // 在这里可以初始化队列，具体看业务
//        rabbitTemplate.execute( channel -> {
////            channel.queueDeclare()  // 可以初始化 队列
//            return null;
//        });
//    }

    @Bean
    public CachingConnectionFactory connectionFactory(){
        // 启动的时候可以设置确认模式，
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses("100.68.2.174");
        cachingConnectionFactory.setUsername("admin");
        cachingConnectionFactory.setPassword("123");
        cachingConnectionFactory.setPublisherConfirms(true);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setConfirmCallback(callback);

        return template;
    }
}
