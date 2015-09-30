package com.antsoft.yecai.mapper;

import com.antsoft.yecai.YecaiTestSuite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by ant on 2015/9/30.
 */
@YecaiTestSuite
@RunWith(SpringJUnit4ClassRunner.class)
public class CheatingUserMapperTest {
    @Autowired
    private CheatingUserMapper cheatingUserMapper;

    @Before
    public void setUp() {
        cheatingUserMapper.clear();
    }

    @After
    public void tearDown() {
        cheatingUserMapper.clear();
    }

    @Test
    public void testAdd() {
        final int count = 10;
        for (int i = 0; i < count; i++) {
            cheatingUserMapper.add("user_1", new BigDecimal(100));
        }
        assertEquals(count, cheatingUserMapper.count());
    }
}
