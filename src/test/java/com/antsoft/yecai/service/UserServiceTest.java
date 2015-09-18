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
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService.clear();
    }

    @After
    public void tearDown() throws Exception {
        userService.clear();
    }

    @Test
    public void testCreate() {
        final String userId = "abc";
        final String userName = "空雅";
        final int lv = 30;
        final int exp = 1000;
        final String remark = "备注信息";
        final int vip = 0;
        createUser(userId, userName, lv, exp, remark, vip);
        User createdUser = userService.getByUserId(userId);
        validateUser(createdUser, userId, userName, lv, exp, remark, vip);
    }

    @Test
    public void testFindByUserName() {
        final String userName = "巨蚁";
        createUser("1", userName, 0, 0, "", 0);
        List<User> users = userService.findByUserName(userName);
        assertNotNull(users);

        assertEquals(userName, users.get(0).getUserName());

        createUser("2", "abc" + userName + "abc", 0, 0, "", 0);
        users = userService.findByUserName(userName);
        assertEquals(2, users.size());
    }

    private void createUser(String userId, String userName,
                            int lv, int exp, String remark, int vip) {
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setLv(lv);
        user.setExp(exp);
        user.setRemark(remark);
        user.setVip(vip);
        userService.create(user);
    }

    private void validateUser(User user, String userId, String userName,
                              int lv, int exp, String remark, int vip) {
        assertNotNull(user);
        assertEquals(userId, user.getUserId());
        assertEquals(userName, user.getUserName());
        assertEquals(lv, user.getLv());
        assertEquals(exp, user.getExp());
        assertEquals(remark, user.getRemark());
        assertEquals(vip, user.getVip());
    }
}
