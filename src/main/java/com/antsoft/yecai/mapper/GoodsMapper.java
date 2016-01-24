package com.antsoft.yecai.mapper;

import com.antsoft.yecai.model.Goods;
import com.antsoft.yecai.type.EquipmentType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ant on 2016/1/24.
 */
public interface GoodsMapper {
    int count();
    void clear();
    void create(Goods goods);
    Goods getByKey(String key);
    List<Goods> getByEquipmentType(@Param("equipmentType") EquipmentType equipmentType,
                                   @Param("pageNo") int pageNo,
                                   @Param("pageSize") int pageSize);
    int countByEquipmentType(EquipmentType equipmentType);
}
