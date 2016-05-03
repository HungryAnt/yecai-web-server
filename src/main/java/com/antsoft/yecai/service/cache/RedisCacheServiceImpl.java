package com.antsoft.yecai.service.cache;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * Created by ant on 2016/5/3.
 */
@Service
public class RedisCacheServiceImpl implements CacheService {
    volatile JedisPool pool;

    @PostConstruct
    private void init() {
        pool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
    }

    @Override
    public void clearAll() {
        try (Jedis jedis = pool.getResource()) {
            jedis.flushDB();
        }
    }

    @Override
    public boolean setValue(String key, String value) {
        try (Jedis jedis = pool.getResource()) {
            String result = jedis.set(key, value);
            return StringUtils.equals(result, "OK");
        }
    }

    @Override
    public String getValue(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        }
    }
}
