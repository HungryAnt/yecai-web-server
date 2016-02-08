package com.antsoft.yecai.mapper;

import com.antsoft.yecai.model.Nutrient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ant on 2015/9/15.
 */
public interface NutrientMapper {
    int count();
    void clear();
    List<Nutrient> getUserNutrientsForUpdate(String userId);
    void updateNutrientCount(Nutrient nutrient);
}
