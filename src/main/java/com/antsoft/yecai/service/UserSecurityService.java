package com.antsoft.yecai.service;

import com.antsoft.yecai.exception.GameExceptions;
import com.antsoft.yecai.mapper.UserRegisterInfoMapper;
import com.antsoft.yecai.model.UserLoginInfo;
import com.antsoft.yecai.model.UserLoginResult;
import com.antsoft.yecai.model.UserRegisterInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by ant on 2015/10/18.
 */
@Service
public class UserSecurityService {
    @Autowired
    private UserRegisterInfoMapper userRegisterInfoMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void clear() {
        userRegisterInfoMapper.clear();
    }

    public void register(UserRegisterInfo userRegisterInfo) {
        if (!StringUtils.isBlank(userRegisterInfo.getRelatedUserId())) {
            userRegisterInfo.setUserId(userRegisterInfo.getRelatedUserId());
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
}
