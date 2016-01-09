package com.antsoft.yecai.mapper;

import com.antsoft.yecai.model.UserEquipment;

import java.util.List;

/**
 * Created by ant on 2016/1/9.
 */
public interface UserEquipmentMapper {
    int count();
    void clear();
    void create(UserEquipment userEquipment);
    List<UserEquipment> getByUserId(String userId);
}
