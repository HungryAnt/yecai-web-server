package com.antsoft.yecai.service;

import com.antsoft.yecai.YecaiTestSuite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ant on 2015/9/16.
 */
@YecaiTestSuite
@RunWith(SpringJUnit4ClassRunner.class)
public class UserVehicleServiceTest {
    @Autowired
    private UserVehicleService userVehicleService;

    @Before
    public void setUp() throws Exception {
        userVehicleService.clear();
    }

    @After
    public void tearDown() throws Exception {
        userVehicleService.clear();
    }

    @Test
    public void testCreate() {
        final String userId = "abc";
        final String vehicle = "vehicle_50";
        userVehicleService.create(userId, vehicle);
        List<String> vehicles = userVehicleService.getVehicles(userId);
        assertEquals(1, vehicles.size());
        assertEquals(vehicle, vehicles.get(0));
        assertEquals(1, userVehicleService.countByUserId(userId));
        userVehicleService.clearByUserId(userId);
        assertEquals(0, userVehicleService.countByUserId(userId));
    }
}
