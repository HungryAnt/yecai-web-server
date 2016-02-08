package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.NutrientMapper;
import com.antsoft.yecai.model.Nutrient;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ant on 2016/2/8.
 */
@Service
public class NutrientService {
    @Autowired
    private NutrientMapper nutrientMapper;

    public List<Nutrient> getUserNutrientsForUpdate(String userId) {
        return nutrientMapper.getUserNutrientsForUpdate(userId);
    }

    void updateNutrientCount(Nutrient nutrient){
        nutrientMapper.updateNutrientCount(nutrient);
    }
}
