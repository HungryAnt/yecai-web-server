package com.antsoft.yecai.mapper;

import java.util.List;

/**
 * Created by ant on 2015/9/15.
 */
public interface UserVehicleMapper {
    int count();
    void clear();
    List<String> getVehicles(String userId);
}
