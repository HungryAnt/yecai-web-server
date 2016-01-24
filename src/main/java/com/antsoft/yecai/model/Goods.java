package com.antsoft.yecai.model;

import com.antsoft.yecai.type.EquipmentType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ant on 2015/9/22.
 */
@Data
@EqualsAndHashCode
public class Goods {
    private long id;
    private EquipmentType equipmentType;
    private String key;
    private long price;
}
