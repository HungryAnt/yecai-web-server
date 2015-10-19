package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.UserRegisterInfoMapper;
import com.antsoft.yecai.model.UserLoginInfo;
import com.antsoft.yecai.model.UserRegisterInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        }
        userRegisterInfo.setPassword(encrypt(userRegisterInfo.getPassword()));
        userRegisterInfoMapper.create(userRegisterInfo);
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
