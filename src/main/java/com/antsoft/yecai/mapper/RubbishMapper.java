package com.antsoft.yecai.mapper;

/**
 * Created by ant on 2015/9/15.
 */
public interface RubbishMapper {
    int count();
    void clear();
    int getAllCountForUpdate(String userId);
    int clearAllCount(String userId);
}
