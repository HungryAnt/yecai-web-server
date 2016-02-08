package com.antsoft.yecai.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by ant on 2016/2/8.
 */
public interface ShitMineMapper {
    int count();
    void clear();

    // 检测执行用户数量 0/1
    int getUserCountByUserId(String userId);

    // 获取炸弹数量
    int getUserShitMineCount(String userId);

    // 创建炸弹
    void create(@Param("userId") String userId, @Param("mineCount") int mineCount);

    void decrease(@Param("userId") String userId);

    void increase(@Param("userId") String userId, @Param("mineCount") int mineCount);
}
