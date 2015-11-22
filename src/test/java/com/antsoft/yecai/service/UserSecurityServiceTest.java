package com.antsoft.yecai.service;

import com.antsoft.yecai.YecaiTestSuite;
import com.antsoft.yecai.exception.GameExceptions;
import com.antsoft.yecai.model.UserLoginInfo;
import com.antsoft.yecai.model.UserRegisterInfo;
import com.antsoft.yecai.type.Gender;
import com.antsoft.yecai.utils.MD5SignatureUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by ant on 2015/10/19.
 */
@YecaiTestSuite
@RunWith(SpringJUnit4ClassRunner.class)
public class UserSecurityServiceTest {
    @Autowired
    private UserSecurityService userSecurityService;

    @Before
    public void setUp() throws Exception {
        userSecurityService.clear();
    }

    @After
    public void tearDown() throws Exception {
        userSecurityService.clear();
    }

    @Test
    public void testRegister() {
        UserRegisterInfo u1 = generateUserRegisterInfo();
        final String loginName = u1.getLoginName();
        userSecurityService.register(u1);
        UserRegisterInfo u2 = userSecurityService.getByLoginName(loginName);
        validateUserRegisterInfo(u1, u2);
    }

    @Test(expected = GameExceptions.LoginUserAlreadyExistsException.class)
    public void testRegisterDuplicateUsers() {
        UserRegisterInfo u1 = generateUserRegisterInfo();
        userSecurityService.register(u1);

        UserRegisterInfo u2 = generateUserRegisterInfo();
        userSecurityService.register(u2);
    }

    public void testLogin() {
        UserRegisterInfo u1 = generateUserRegisterInfo();
        userSecurityService.register(u1);
        UserLoginInfo userLoginInfo = new UserLoginInfo();
        userLoginInfo.setLoginName(u1.getLoginName());
        userLoginInfo.setPassword(u1.getPassword());
        assertTrue(userSecurityService.login(userLoginInfo).isSuccess());

        userLoginInfo.setLoginName("xx");
        userLoginInfo.setPassword("xx");
        assertFalse(userSecurityService.login(userLoginInfo).isSuccess());
    }

    private void validateUserRegisterInfo(UserRegisterInfo u1, UserRegisterInfo u2) {
        assertEquals(u1.getLoginName(), u2.getLoginName());
        assertEquals(u1.getPassword(), u2.getPassword());
        assertEquals(u1.getUserId(), u2.getUserId());
        assertEquals(u1.getGender(), u2.getGender());
        assertEquals(u1.getQq(), u2.getQq());
        assertEquals(u1.getEmail(), u2.getEmail());
        assertEquals(u1.getIntroduction(), u2.getIntroduction());
    }

    private static UserRegisterInfo generateUserRegisterInfo() {
        UserRegisterInfo userRegisterInfo = new UserRegisterInfo();
        userRegisterInfo.setLoginName("Ant");
        userRegisterInfo.setPassword("password");
        userRegisterInfo.setUserId("userId");
        userRegisterInfo.setGender(Gender.Male);
        userRegisterInfo.setQq("517377100");
        userRegisterInfo.setEmail("517377100@qq.com");
        userRegisterInfo.setIntroduction("introduction");
        userRegisterInfo.setSign(MD5SignatureUtil.getSignAsHex(
                userRegisterInfo.getLoginName() +userRegisterInfo.getPassword() + "AntRegister"));
        return userRegisterInfo;
    }
}
