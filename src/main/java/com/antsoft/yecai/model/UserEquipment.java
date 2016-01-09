package com.antsoft.yecai.model;

import com.antsoft.yecai.type.EquipmentType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ant on 2016/1/9.
 */
@Data
@EqualsAndHashCode
public class UserEquipment {
    private long id;
    private String userId;
    private EquipmentType equipmentType;
    private String equipmentKey;
}
