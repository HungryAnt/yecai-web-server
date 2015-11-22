package com.antsoft.yecai.mapper;

import com.antsoft.yecai.model.UserPet;

import java.util.List;

/**
 * Created by ant on 2015/11/22.
 */
public interface UserPetMapper {
    int count();
    void clear();
    void create(UserPet userPet);
    UserPet getByPetId(String petId);
    List<UserPet> getPetsByUserId(String userId);
}
