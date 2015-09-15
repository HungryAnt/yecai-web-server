package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.UserVehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Created by ant on 2015/9/15.
 */
@Service
public class UserVehicleService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserVehicleMapper userVehicleMapper;

    private Random random = new Random();

    public void gift(String userId) {
        int level = userService.getUserLevel(userId);
        if (level < 30) {
            return;
        }

    }

    public List<String> getVehicles(String userId) {
        return userVehicleMapper.getVehicles(userId);
    }

    private String randomVehicle() {
        int[] nums = {39, 40, 50, 58, 59, 67, 74, 75, 81, 82, 83, 89, 90, 91};
        int num = random.nextInt(nums.length);
        return String.format("vehicle_%s", num);
    }
}
