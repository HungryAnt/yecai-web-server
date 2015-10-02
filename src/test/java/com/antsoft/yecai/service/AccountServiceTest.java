package com.antsoft.yecai.service;

import com.antsoft.yecai.YecaiTestSuite;
import com.antsoft.yecai.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ant on 2015/9/16.
 */
@YecaiTestSuite
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        accountService.clear();
    }

    @After
    public void tearDown() throws Exception {
        accountService.clear();
    }

    @Test
    public void testUpdateAmount() {
//        final String userId = "abc";
//        accountService.updateAmount(userId, 100);
//        assertEquals(100, accountService.getAmount(userId));
    }
}
