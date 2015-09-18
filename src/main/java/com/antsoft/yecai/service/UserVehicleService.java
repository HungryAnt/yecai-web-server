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

    public void clear() {
        userVehicleMapper.clear();
    }

    public void gift(String userId) {
        int level = userService.getLevel(userId);
        if (level < 30) {
            return;
        }
        String vehicle = randomVehicle();
        create(userId, vehicle);
    }

    public List<String> getVehicles(String userId) {
        return userVehicleMapper.getVehicles(userId);
    }

    public String randomVehicle() {
        int[] nums = {39, 40, 50, 58, 59, 67, 74, 75, 81, 82, 83, 89, 90, 91};
        int index = random.nextInt(nums.length);
        return String.format("vehicle_%s", nums[index]);
    }

    public void create(String userId, String vehicle) {
        userVehicleMapper.create(userId, vehicle);
    }

    public String toVehicle(int num) {
        return "vehicle_" + num;
    }
}
