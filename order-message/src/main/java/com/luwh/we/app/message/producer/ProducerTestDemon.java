package com.luwh.we.app.message.producer;

import com.luwh.we.app.message.MessageContent;
import com.luwh.we.app.message.constants.Constants;
import com.luwh.we.app.message.utils.MessageIdGenerator;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lu.wh
 * @date 2023/10/07 16/07/16
 * @description
 */
@Component
public class ProducerTestDemon {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        String messageId = MessageIdGenerator.generate();
        // 发送的时候构建这个对象，可以为消息做一个唯一id,, 这样回调的时候就能作为凭证
        CorrelationData correlationData = new CorrelationData(messageId);
        MessageContent mes = new MessageContent();
        mes.setBody(message);
        mes.setId(messageId);
        mes.setType((short) 0);
        rabbitTemplate.convertAndSend("amq.direct", Constants.MQ_DEFAULT_QUEUE, mes, correlationData);
        System.out.println("mq send message queue:[" + Constants.MQ_DEFAULT_QUEUE + ", message:[" + message + "]");
    }
}
