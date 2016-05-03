package com.antsoft.yecai.service.cache;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ant on 2016/5/3.
 */
@Service
@Primary
public class MemoryCacheServiceImpl implements CacheService {
    private Map<String, String> cache = new HashMap<>();

    @Override
    public void clearAll() {
        cache.clear();
    }

    @Override
    public boolean setValue(String key, String value) {
        cache.put(key, value);
        return true;
    }

    @Override
    public String getValue(String key) {
        return cache.get(key);
    }
}
