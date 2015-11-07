package com.antsoft.yecai.service;

import com.antsoft.yecai.YecaiTestSuite;
import com.antsoft.yecai.mapper.PromotionLinkStatMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by ant on 2015/11/7.
 */
@YecaiTestSuite
@RunWith(SpringJUnit4ClassRunner.class)
public class PromotionLinkStatServiceTest {
    @Autowired
    private PromotionLinkStatService promotionLinkStatService;

    @Autowired
    private PromotionLinkStatMapper promotionLinkStatMapper;

    @Test
    public void testIncreaseCount() {
        final String name = "game";
        assertNull(promotionLinkStatMapper.getPromotionCountByName(name));
        promotionLinkStatService.increaseCount(name);
        assertEquals(1L, promotionLinkStatMapper.getPromotionCountByName(name).longValue());
        promotionLinkStatService.increaseCount(name);
        assertEquals(2L, promotionLinkStatMapper.getPromotionCountByName(name).longValue());
    }
}
