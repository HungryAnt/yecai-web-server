package com.antsoft.yecai.service;

import com.antsoft.yecai.exception.GameExceptions;
import com.antsoft.yecai.mapper.UserRegisterInfoMapper;
import com.antsoft.yecai.model.UserLoginInfo;
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

    public boolean login(UserLoginInfo userLoginInfo) {
        return verify(userLoginInfo.getLoginName(), userLoginInfo.getPassword());
    }

    public UserRegisterInfo getByLoginName(String loginName) {
        return userRegisterInfoMapper.getByLoginName(loginName);
    }

    private boolean verify(String loginUser, String password) {
        String encodedPassword = userRegisterInfoMapper.getEncodedPassword(loginUser);
        if (encodedPassword == null) {
            return false;
        }
        return passwordEncoder.matches(password, encodedPassword);
    }

    private String encrypt(String password) {
        return passwordEncoder.encode(password);
    }
}
