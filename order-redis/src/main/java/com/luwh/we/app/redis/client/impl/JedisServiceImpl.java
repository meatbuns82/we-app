package com.luwh.we.app.redis.client.impl;

import com.luwh.we.app.redis.client.JedisPoolUtil;
import com.luwh.we.app.redis.client.JedisService;
import com.luwh.we.app.redis.config.JedisConfigProperty;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Tuple;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * @author lu.wh
 * @date 2023/11/08 14/02/37
 * @description
 */
@Service
public class JedisServiceImpl implements JedisService {
    @Resource
    private JedisConfigProperty jedisConfigProperty;

    private JedisPoolUtil jedisPoolUtil;

    @PostConstruct
    public void init(){
        jedisPoolUtil = new JedisPoolUtil(jedisConfigProperty);
    }

    public Double zScore(String key, String member){
        Map<String, String> stringStringMap = jedisPoolUtil.getJedis().hgetAll("hash-key");
        Double zscore = jedisPoolUtil.getJedis().zscore(key, member);
        return zscore;
    }

    public long sAdd(String key, String member){
        long sAdd = jedisPoolUtil.getJedis().sadd(key, member);
        return sAdd;
    }

    @Override
    public void zincrBy(String key, String member, Integer increment) {
        jedisPoolUtil.getJedis().zincrby(key, increment, member);
    }

    @Override
    public void hincrBy(String key, String field, int value) {
        jedisPoolUtil.getJedis().hincrBy(key, field, value);
    }

    @Override
    public void expire(String key, Integer expire) {
        jedisPoolUtil.getJedis().expire(key, expire);
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        String hmset = jedisPoolUtil.getJedis().hmset(key, hash);
        return hmset;
    }

    @Override
    public void zAdd(String key, String member, double score) {
        jedisPoolUtil.getJedis().zadd(key, score, member);
    }

    @Override
    public  Set<Tuple> zrevrange(String key, Integer start, Integer end) {
        Set<Tuple> zrevrange = jedisPoolUtil.getJedis().zrevrangeWithScores(key, start, end);
        return zrevrange;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Map<String, String> map = jedisPoolUtil.getJedis().hgetAll(key);
        return map;
    }

    @Override
    public void srem(String key, String member) {
        Long srem = jedisPoolUtil.getJedis().srem(key, member);
    }

    @Override
    public Boolean exeistKey(String key) {
        Boolean exists = jedisPoolUtil.getJedis().exists(key);
        return exists;
    }

    @Override
    public void zinterstore(String destKey, String... keys) {
        jedisPoolUtil.getJedis().zinterstore(destKey, keys);
        Pipeline pipelined = jedisPoolUtil.getJedis().pipelined();
    }

    @Override
    public void hdel(String key, String... item) {
        jedisPoolUtil.getJedis().hdel(key, item);
    }

    @Override
    public void hset(String key, String item, String count) {
        jedisPoolUtil.getJedis().hset(key, item, count);
    }
}
