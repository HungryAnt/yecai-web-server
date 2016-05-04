package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.UserPetMapper;
import com.antsoft.yecai.model.UserPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by ant on 2015/11/22.
 */
@Service
public class UserPetService {
    @Autowired
    private UserPetMapper userPetMapper;

    @Autowired
    private PetLevelService petLevelService;

    public void clear() {
        userPetMapper.clear();
    }

    public int count() {
        return userPetMapper.count();
    }

    public UserPet create(String userId, String petType) {
        String petId = UUID.randomUUID().toString();
        UserPet userPet = new UserPet();
        userPet.setPetId(petId);
        userPet.setPetType(petType);
        userPet.setUserId(userId);
        userPetMapper.create(userPet);
        return userPet;
    }

    public UserPet getByPetId(String petId) {
        UserPet userPet = userPetMapper.getByPetId(petId);
        userPet.setLevel(petLevelService.getLevel(petId));
        return userPet;
    }

    public List<UserPet> getPetsByUserId(String userId) {
        List<UserPet> userPets = userPetMapper.getPetsByUserId(userId);
        for (UserPet userPet : userPets) {
            userPet.setLevel(petLevelService.getLevel(userPet.getPetId()));
        }
        return userPets;
    }
}
