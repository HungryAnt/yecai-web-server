package com.antsoft.yecai.service;

import com.antsoft.yecai.model.Level;
import com.antsoft.yecai.service.cache.CacheService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ant on 2016/5/3.
 */
@Service
public class PetLevelService {
    public static final long EXP_PER_LV = 200;

    @Autowired
    private CacheService cacheService;

    public long getExp(String petId) {
        String value = cacheService.getValue(toKey(petId));
        if (StringUtils.isBlank(value)) {
            return 0;
        }
        return Long.parseLong(value);
    }

    public Level getLevel(String petId) {
        long exp = getExp(petId);
        return toLevel(exp);
    }

    public void setExp(String petId, long exp) {
        cacheService.setValue(toKey(petId), String.valueOf(exp));
    }

    private static String toKey(String petId) {
        return "pet_" + petId + "_exp";
    }

    // expPerLv * (lv - 1) * lv / 2 = expLvBase
    // expLvBase 稍小于 expTotal
    public static Level toLevel(long expTotal) {
        double a = 1.0;
        double b = -1.0;
        double c = -2.0 * expTotal / EXP_PER_LV;
        final long lv = (long)((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));
        long expLvBase = EXP_PER_LV * (lv - 1) * lv / 2;
        final long expInLv = expTotal - expLvBase;
        final long maxExpInLv = EXP_PER_LV * lv;
        return new Level() {
            {
                setLv(lv);
                setExpInLv(expInLv);
                setMaxExpInLv(maxExpInLv);
            }
        };
    }
}
