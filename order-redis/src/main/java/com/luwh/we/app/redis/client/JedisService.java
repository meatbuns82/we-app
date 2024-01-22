package com.luwh.we.app.redis.client;

import redis.clients.jedis.Tuple;

import java.util.Map;
import java.util.Set;

/**
 * @author lu.wh
 * @date 2023/11/08 14/02/20
 * @description
 */
public interface JedisService {
    Double zScore(String key, String member);
    long sAdd(String key, String member);

    void zincrBy(String key, String member, Integer increment);

    void hincrBy(String key, String field, int value);

    void expire(String food, Integer oneWeekInSeconds);

    String hmset(String key, Map<String, String> hash) ;

    void zAdd(String key, String member, double score);

    Set<Tuple> zrevrange(String key, Integer start, Integer end);

    Map<String, String> hgetAll(String element);

    void srem(String key, String member);

    Boolean exeistKey(String key);

    void zinterstore(String destKey, String... keys);

    public void hdel(String key, String... item);

    void hset(String key, String item, String count);
}
