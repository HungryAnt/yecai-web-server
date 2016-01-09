package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.UserEquipmentMapper;
import com.antsoft.yecai.model.UserEquipment;
import com.antsoft.yecai.type.EquipmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ant on 2016/1/9.
 */
@Service
public class UserEquipmentService {
    @Autowired
    private UserEquipmentMapper userEquipmentMapper;

    public int count() {
        return userEquipmentMapper.count();
    }

    public void clear() {
        userEquipmentMapper.clear();
    }

    public void create(String userId, EquipmentType equipmentType, String equipmetnKey) {
        UserEquipment userEquipment = new UserEquipment();
        userEquipment.setUserId(userId);
        userEquipment.setEquipmentType(equipmentType);
        userEquipment.setEquipmentKey(equipmetnKey);
        create(userEquipment);
    }

    public void create(UserEquipment userEquipment) {
        userEquipmentMapper.create(userEquipment);
    }

    public List<UserEquipment> getUserEquipments(String userId) {
        return userEquipmentMapper.getByUserId(userId);
    }
}
