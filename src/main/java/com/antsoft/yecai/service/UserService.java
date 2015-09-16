package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ant on 2015/9/15.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int getLevel(String userId) {
        return userMapper.getLevel(userId);
    }
}
