package com.antsoft.yecai.service;

import com.antsoft.yecai.YecaiTestSuite;
import com.antsoft.yecai.mapper.ShitMineMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ant on 2016/2/8.
 */
@YecaiTestSuite
@RunWith(SpringJUnit4ClassRunner.class)
public class ShitMineServiceTest {
    @Autowired
    private ShitMineService shitMineService;

    @Autowired
    private ShitMineMapper shitMineMapper;

    @Before
    @After
    public void clear() throws Exception {
        shitMineMapper.clear();
    }

    @Test
    public void testGetUserShitMineCount() throws Exception {
        final String userId = "userId";
        assertFalse(shitMineService.hasShitMine(userId));
        assertEquals(ShitMineService.USER_INIT_SHIT_MINE_COUNT,
                shitMineService.getUserShitMineCount(userId));
        assertTrue(shitMineService.hasShitMine(userId));
    }
}
