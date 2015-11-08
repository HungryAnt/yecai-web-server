package com.antsoft.yecai.service;

import com.antsoft.yecai.exception.GameExceptions;
import com.antsoft.yecai.mapper.UserRegisterInfoMapper;
import com.antsoft.yecai.model.UserLoginInfo;
import com.antsoft.yecai.model.UserLoginResult;
import com.antsoft.yecai.model.UserRegisterInfo;
import com.antsoft.yecai.utils.MD5SignatureUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by ant on 2015/10/18.
 */
@Service
public class UserSecurityService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRegisterInfoMapper userRegisterInfoMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void clear() {
        userRegisterInfoMapper.clear();
    }

    public void register(UserRegisterInfo userRegisterInfo) {
        CheckSign(userRegisterInfo.getSign(),
                userRegisterInfo.getLoginName() + userRegisterInfo.getPassword() + "AntRegister");

        if (!StringUtils.isBlank(userRegisterInfo.getRelatedUserId())) {
            userRegisterInfo.setUserId(userRegisterInfo.getRelatedUserId());
            if (userRegisterInfoMapper.checkUserId(userRegisterInfo.getRelatedUserId()) != null) {
                throw new GameExceptions.UserIdAlreadyExistsException();
            }
        } else {
            userRegisterInfo.setUserId(UUID.randomUUID().toString());
        }
        userRegisterInfo.setPassword(encrypt(userRegisterInfo.getPassword()));
        try {
            userRegisterInfoMapper.create(userRegisterInfo);
        } catch (DuplicateKeyException ex) {
            throw new GameExceptions.LoginUserAlreadyExistsException(userRegisterInfo.getLoginName());
        }
    }

    public UserLoginResult login(UserLoginInfo userLoginInfo) {
        CheckSign(userLoginInfo.getSign(),
                userLoginInfo.getLoginName() + userLoginInfo.getPassword() + "AntLogin");
        return verify(userLoginInfo.getLoginName(), userLoginInfo.getPassword());
    }

    public UserRegisterInfo getByLoginName(String loginName) {
        return userRegisterInfoMapper.getByLoginName(loginName);
    }

    private UserLoginResult verify(String loginName, String password) {
        UserLoginResult userLoginResult = new UserLoginResult();
        String encodedPassword = userRegisterInfoMapper.getEncodedPassword(loginName);
        if (encodedPassword == null) {
            return userLoginResult;
        }
        boolean success = passwordEncoder.matches(password, encodedPassword);
        userLoginResult.setSuccess(success);
        if (success) {
            userLoginResult.setUserId(userRegisterInfoMapper.getUserId(loginName));
        }
        return userLoginResult;
    }

    private String encrypt(String password) {
        return passwordEncoder.encode(password);
    }

    private void CheckSign(String sign, String text) {
        if (StringUtils.isBlank(sign)) {
            throw new GameExceptions.SignNotMatchException();
        }
        String expectedSign = MD5SignatureUtil.getSignAsHex(text);
        logger.info("sign:{} expectedSign:{}", sign, expectedSign);
        if (!sign.equals(expectedSign)) {
            throw new GameExceptions.SignNotMatchException();
        }
    }
}
