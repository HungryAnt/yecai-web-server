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

    // expPerLv * (lv - 1) * lv / 2 = lvBaseExp
    // lvBaseExp 稍小于 expTotal
    public static Level toLevel(long expTotal) {
        final long expPerLv = 200;
        double a = 1.0;
        double b = -1.0;
        double c = -2.0 * expTotal / expPerLv;
        final long lv = (long)((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));
        long expLvBase = expPerLv * (lv - 1) * lv / 2;
        final long expInLv = expTotal - expLvBase;
        return new Level() {
            {
                setLv(lv);
                setExpInLv(expInLv);
            }
        };
    }
}
