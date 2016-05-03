package com.antsoft.yecai.service.cache;

/**
 * Created by ant on 2016/5/3.
 */
public interface CacheService {
    public void clearAll();
    boolean setValue(String key, String value);
    String getValue(String key);
}
