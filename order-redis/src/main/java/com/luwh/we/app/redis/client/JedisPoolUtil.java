package com.luwh.we.app.redis.client;

import com.luwh.we.app.redis.config.JedisConfigProperty;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author lu.wh
 * @date 2023/11/08 11/54/35
 * @description
 */
public class JedisPoolUtil {
    private static JedisPool jedisPool;

    public JedisPoolUtil(JedisConfigProperty config) {
        initPool(config);
    }

    public void initPool(JedisConfigProperty config){
        JedisPoolConfig jedisConfig = new JedisPoolConfig();
        jedisConfig.setMaxTotal(config.getMaxTotal());
        jedisConfig.setMaxIdle(config.getMaxIdle());
        jedisConfig.setBlockWhenExhausted(config.getBlockWhenExhausted());
        jedisConfig.setMaxWaitMillis(config.getMaxWaitMillis());
        jedisConfig.setTestOnBorrow(config.isTestOnBorrow());
        jedisPool = new JedisPool(jedisConfig, config.getHost(), config.getPort(), 20 * 100, config.getPassword());
    }

    public Jedis getJedis(){
        Jedis resource = jedisPool.getResource();
        return resource;
    }
}
