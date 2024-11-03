package com.luwh.we.app.message.consumer;

import com.alibaba.fastjson.JSONObject;
import com.luwh.we.app.common.exception.exceptions.OrderException;
import com.luwh.we.app.message.MessageContent;
import com.luwh.we.app.message.constants.Constants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 消费者，消费消息，目前是单线程消费，后续考虑多线程消费（需要结合场景）
 *
 * @author lu.wh
 * @date 2023/10/07 16/07/26
 * @description
 */
@Component
//@RabbitListener(queues = Constants.MQ_DEFAULT_QUEUE)
public class ConsumerTestDemon implements Runnable {
    @Resource
    private RabbitTemplate rabbitTemplate;

    private Long receive_message_interval = 1000L;
    public Message receive() {

        Message receive = rabbitTemplate.receive(Constants.MQ_DEFAULT_QUEUE);
        return receive;
    }

    @Override
    public void run() {
        // 循环消费
        while (true) {
            Message receive = receive();
            synchronized (this) {
                if (receive != null) {
                    byte[] body = receive.getBody();
                    MessageContent message = JSONObject.parseObject(new String(body, StandardCharsets.UTF_8), MessageContent.class);
                    System.out.println("mq receive message, content:[" + message + "]");
                } else {
                    try {
                        // 等待
                        wait(receive_message_interval);
                    } catch (InterruptedException e) {
                        throw new OrderException(e.toString());
                    }
                }
            }
        }
    }
}
