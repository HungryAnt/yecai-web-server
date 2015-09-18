package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.UserMapper;
import com.antsoft.yecai.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ant on 2015/9/15.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void clear() {
        userMapper.clear();
    }

    public void create(User user) {
        userMapper.create(user);
    }

    public User getByUserId(String userId) {
        return userMapper.getByUserId(userId);
    }

    public int getLevel(String userId) {
        return userMapper.getLevel(userId);
    }

    public List<User> findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }
}
