package com.luwh.baby.order.test.redis;

import com.luwh.we.app.redis.client.JedisService;
import com.luwh.we.app.server.BabyOrderApplication;
import com.luwh.we.app.service.vote.VoteService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.server.Session;

import javax.annotation.Resource;


/**
 * @author lu.wh
 * @date 2023/11/08 14/49/33
 * @description
 */
@SpringBootTest(classes = BabyOrderApplication.class)
public class VoteTest {
    @Resource
    private VoteService voteService;
    @Resource
    private JedisService jedisService;

    @Test
    public void voteTest() {
        voteService.vote("106952146");
    }

    @Test
    public void publishTest() {
        voteService.publish("104363872");
    }

    @Test
    public void foodOrderTest() {
        voteService.foodOrder(0, 100);
    }

    @Test
    public void classifyFoodTest(){
        voteService.classifyFood("104363872", "gdhxgikz2", true);
    }

    @Test
    public void getGroupFoodTest(){
        voteService.getGroupFood("gdhxgikz2");
    }

    // redis测试
    // 购物车实现
    @Test
    public void addToCart(){
        int count = 0;
        Session session = new Session(); // 用户的 会话缓存，购物车是和每个用户绑定的
        String item = "";
        if(count <= 0){
            jedisService.hdel("cart:" + session, item);
        }else {
            jedisService.hset("cart:" + session, item, count + ""); // 将制定商品添加到购物车，v是数量
        }
    }

    @Test
    public void cleanFullSession(){
//        jedisService.zcard("recent:");
    }
}
