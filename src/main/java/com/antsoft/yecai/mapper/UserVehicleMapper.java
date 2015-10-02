package com.antsoft.yecai.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ant on 2015/9/15.
 */
public interface UserVehicleMapper {
    int count();
    void clear();
    List<String> getVehicles(String userId);
    void create(@Param("userId") String userId, @Param("vehicle") String vehicle);
    int countByUserId(String userId);
    void clearByUserId(String userId);
}
