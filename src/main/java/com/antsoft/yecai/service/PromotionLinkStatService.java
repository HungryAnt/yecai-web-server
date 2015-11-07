package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.PromotionLinkStatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * Created by ant on 2015/11/7.
 */
@Service
public class PromotionLinkStatService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PromotionLinkStatMapper promotionLinkStatMapper;

    public void increaseCount(String name) {
        ensureExists(name);
        promotionLinkStatMapper.increaseCount(name);
    }

    private void ensureExists(String name) {
        Long promotionCount = promotionLinkStatMapper.getPromotionCountByName(name);
        if (promotionCount == null) {
            try {
                promotionLinkStatMapper.create(name);
            } catch (DuplicateKeyException ex) {
                logger.warn("duplicated PromotionLinkStat already exists name:" + name, ex);
            }
        }
    }
}
