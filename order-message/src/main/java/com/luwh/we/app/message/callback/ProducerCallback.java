package com.luwh.we.app.message.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 *  只有  confirm的模式下才会有效果
 *
 * @author lu.wh
 * @date 2023/10/07 17/54/35
 * @description
 */
@Component
public class ProducerCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("======================");
        System.out.println(correlationData);
        System.out.println(ack);
        System.out.println(cause);
        System.out.println("======================");
        if(ack){
            // 消息确认

        }else {
//            ReturnedMessage returned = correlationData.getReturned();
//            Message message = returned.getMessage();
//            MessageProperties messageProperties = message.getMessageProperties();
//            String messageId = messageProperties.getMessageId();
            // 消息未确认
        }
    }
}
