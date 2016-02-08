package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.ShitMineMapper;
import com.antsoft.yecai.model.Nutrient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ant on 2016/2/8.
 */
@Service
public class ShitMineService {
    public static final int USER_INIT_SHIT_MINE_COUNT = 600;

    @Autowired
    private ShitMineMapper shitMineMapper;

    @Autowired
    private NutrientService nutrientService;

    public int getUserShitMineCount(String userId) {
        if (hasShitMine(userId)) {
            return shitMineMapper.getUserShitMineCount(userId);
        } else {
            shitMineMapper.create(userId, USER_INIT_SHIT_MINE_COUNT);
            return USER_INIT_SHIT_MINE_COUNT;
        }
    }

    public boolean hasShitMine(String userId) {
        return shitMineMapper.getUserCountByUserId(userId) == 1;
    }

    @Transactional
    public void exchangeShitMines(String userId) {
        List<Nutrient> nutrients = nutrientService.getUserNutrientsForUpdate(userId);
        final int nutrientDecCount = 10;
        final int shitMineCount = 50;
        for (Nutrient nutrient : nutrients) {
            if (nutrient.getCount() < nutrientDecCount) {
                throw new RuntimeException(
                        String.format("nutrient count is not enough, userId:%s typeId:%d count:%d",
                                nutrient.getUserId(), nutrient.getTypeId(), nutrient.getCount()));
            }
            nutrient.setCount(nutrient.getCount() - nutrientDecCount);
        }
        for (Nutrient nutrient : nutrients) {
            nutrientService.updateNutrientCount(nutrient);
        }
        shitMineMapper.increase(userId, shitMineCount);
    }
}
