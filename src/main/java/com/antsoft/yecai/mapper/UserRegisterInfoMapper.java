package com.antsoft.yecai.mapper;

import com.antsoft.yecai.model.UserRegisterInfo;

/**
 * Created by ant on 2015/10/18.
 */
public interface UserRegisterInfoMapper {
    int count();
    void clear();
    void create(UserRegisterInfo userRegisterInfo);
    String getEncodedPassword(String loginName);
    String getUserId(String loginName);
    UserRegisterInfo getByLoginName(String loginName);
    String checkUserId(String userId);
}
