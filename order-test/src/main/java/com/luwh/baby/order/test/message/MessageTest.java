package com.luwh.baby.order.test.message;


import com.luwh.we.app.message.consumer.ConsumerTestDemon;
import com.luwh.we.app.message.producer.ProducerTestDemon;
import com.luwh.we.app.server.BabyOrderApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author lu.wh
 * @date 2023/10/07 16/19/29
 * @description
 */
@SpringBootTest(classes = BabyOrderApplication.class)
public class MessageTest {
    @Resource
    private ProducerTestDemon producerTestDemon;
    @Resource
    private ConsumerTestDemon consumerTestDemon;

    @Test
    public void ProduceTest(){
        try {
            new Thread(consumerTestDemon).start();
            for (int i = 0; i < 20; i++) {
                producerTestDemon.send("message: " + i);
            }
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
