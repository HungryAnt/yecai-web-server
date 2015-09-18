package com.antsoft.yecai.mapper;

import com.antsoft.yecai.model.User;

import java.util.List;

/**
 * Created by ant on 2015/9/16.
 */
public interface UserMapper {
    void clear();
    void create(User user);
    User getByUserId(String userId);
    int getLevel(String userId);
    List<User> findByUserName(String userName);
}
