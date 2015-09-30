package com.antsoft.yecai.mapper;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * Created by ant on 2015/9/30.
 */
public interface CheatingUserMapper {
    int count();
    void clear();
    void add(@Param("userId") String userId, @Param("speedUpRate") BigDecimal speedUpRate);
}
