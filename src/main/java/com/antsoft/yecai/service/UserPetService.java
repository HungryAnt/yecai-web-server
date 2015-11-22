package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.UserPetMapper;
import com.antsoft.yecai.model.UserPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ant on 2015/11/22.
 */
@Service
public class UserPetService {
    @Autowired
    private UserPetMapper userPetMapper;

    public void clear() {
        userPetMapper.clear();
    }

    public int count() {
        return userPetMapper.count();
    }

    public void create(String userId, String petId) {
        UserPet userPet = new UserPet();
        userPet.setPetId(petId);
        userPet.setUserId(userId);
        userPetMapper.create(userPet);
    }

    public UserPet getByPetId(String petId) {
        return userPetMapper.getByPetId(petId);
    }

    public List<UserPet> getPetsByUserId(String userId) {
        return userPetMapper.getPetsByUserId(userId);
    }
}
