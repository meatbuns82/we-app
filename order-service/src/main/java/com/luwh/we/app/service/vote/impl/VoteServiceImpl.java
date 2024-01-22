package com.luwh.we.app.service.vote.impl;

import com.luwh.we.app.common.constants.VoteConstants;
import com.luwh.we.app.redis.client.JedisService;
import com.luwh.we.app.service.vote.VoteService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Tuple;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lu.wh
 * @date 2023/11/08 14/21/28
 * @description
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Resource
    private JedisService jedisService;

    private static final String SCORE_KEY = "score";
    private static final String VOTES_KEY = "votes";
    private static final String VOTE_PREF_FIX_KEY = "vote_";
    private static final String FOOD_PRE_FIX_KEY = "food_";
    private static final String TIME_KEY = "time";
    @Override
    public void vote(String food) {
        String user = "2";
        // 计算截止的时间
        Long oneWeekInSeconds = System.currentTimeMillis() -  VoteConstants.ONE_WEEK_IN_SECONDS;
        Double time = jedisService.zScore("time", food);
        // 看 食物的投票期是不是已经过期了
        if(time != null && time < oneWeekInSeconds){
            return;
        }
        long l = jedisService.sAdd(VOTE_PREF_FIX_KEY + food, user);
        if(l > 0){
            jedisService.zincrBy(SCORE_KEY, FOOD_PRE_FIX_KEY + food, VoteConstants.VOTE_SCORE);
            jedisService.hincrBy(FOOD_PRE_FIX_KEY + food, VOTES_KEY, 1);
        }
    }

    @Override
    public void publish(String food) {
        String user = "1";
        // 发布一个食物，并且初始化这个食物的分数，投票过期时间 // 默认谁发布的食物，就会默认给这个食物投上一票
        jedisService.sAdd(VOTE_PREF_FIX_KEY + food, user);
        // 给发布的食物设置过期时间
        jedisService.expire(VOTE_PREF_FIX_KEY + food, VoteConstants.ONE_WEEK_IN_SECONDS.intValue());
        long now = System.currentTimeMillis();
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("foodName", "传统炒猪肉");
        hashMap.put("time", now + "");
        hashMap.put("votes", "1");
        // 食物的信息
        jedisService.hmset(FOOD_PRE_FIX_KEY + food, hashMap);
        // 这个食物的初始化分数
        jedisService.zAdd(SCORE_KEY, FOOD_PRE_FIX_KEY + food, now + VoteConstants.VOTE_SCORE);
        // 这个食物的发布时间
        jedisService.zAdd(TIME_KEY, FOOD_PRE_FIX_KEY + food, now);
    }

    @Override
    public void foodOrder(Integer start, Integer end) {
        Set<Tuple> zrevrange = jedisService.zrevrange(SCORE_KEY, start, end);
        Iterator<Tuple> iterator = zrevrange.iterator();
        List<Map<String, String>> objects = new ArrayList<>();
        while (iterator.hasNext()) {
            Tuple next = iterator.next();
            String element = next.getElement();
            Map<String, String> map = jedisService.hgetAll(element);
            map.put("food", element);
            map.put("score", next.getScore() + "");
            objects.add(map);
        }
        System.out.println(objects);
    }

    @Override
    public void classifyFood(String food, String group, boolean increase) {
        if(increase){
            jedisService.sAdd(group, FOOD_PRE_FIX_KEY + food);
        }else {
            jedisService.srem(group, FOOD_PRE_FIX_KEY + food);
        }
    }

    public void getGroupFood(String group){
        String key =  SCORE_KEY + group;
        Boolean aBoolean = jedisService.exeistKey(key);
        if(!aBoolean){
            jedisService.zinterstore(key, group, SCORE_KEY);
            jedisService.expire(key, 60);
        }
        foodOrder(0, -1);
    }
}
