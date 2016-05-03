package com.antsoft.yecai.service;

import com.antsoft.yecai.YecaiTestSuite;
import com.antsoft.yecai.model.Level;
import com.antsoft.yecai.service.cache.CacheService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by ant on 2016/5/3.
 */
@YecaiTestSuite
@RunWith(SpringJUnit4ClassRunner.class)
public class PetLevelServiceTest {
    @Autowired
    private PetLevelService petLevelService;

    @Autowired
    private CacheService cacheService;

    @Before
    @After
    public void clearAll() throws Exception {
        cacheService.clearAll();
    }

    @Test
    public void testGetExp() throws Exception {
        long exp = petLevelService.getExp("pet_id");
        assertEquals(0, exp);
    }

    @Test
    public void testSetValue() throws Exception {
        final String petId = "pet_id";
        final long exp = 10000;
        petLevelService.setExp(petId, exp);
        long actualExp = petLevelService.getExp(petId);
        assertEquals(exp, actualExp);
    }

    @Test
    public void testToLevel() throws Exception {
        assertLevel(0, 1, 0);
        assertLevel(100, 1, 100);
        assertLevel(200, 2, 0);
        assertLevel(400, 2, 200);
        assertLevel(600, 3, 0);
        assertLevel(1200, 4, 0);
        assertLevel(2000, 5, 0);
        assertLevel(2999, 5, 999);
        assertLevel(3000, 6, 0);
        assertLevel(3001, 6, 1);
        assertLevel(38000, 20, 0);
    }

    private void assertLevel(long lvTotal, long expectedLv, long expectedExpInLv) {
        Level level = PetLevelService.toLevel(lvTotal);
        assertEquals(expectedLv, level.getLv());
        assertEquals(expectedExpInLv, level.getExpInLv());
    }
}
