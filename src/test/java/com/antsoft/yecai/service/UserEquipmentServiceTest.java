package com.antsoft.yecai.service;

import com.antsoft.yecai.YecaiTestSuite;
import com.antsoft.yecai.model.UserEquipment;
import com.antsoft.yecai.type.EquipmentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ant on 2016/1/9.
 */
@YecaiTestSuite
@RunWith(SpringJUnit4ClassRunner.class)
public class UserEquipmentServiceTest {
    @Autowired
    private UserEquipmentService userEquipmentService;

    @Before
    public void setUp() throws Exception {
        userEquipmentService.clear();
    }

    @After
    public void tearDown() throws Exception {
        userEquipmentService.clear();
    }

    @Test
    public void testCreate(){
        final String userId = "abc";
        UserEquipment userEquipment = createUserEquipment(userId, EquipmentType.Wing, "wing_xxxx");
        assertEquals(1, userEquipmentService.count());
        List<UserEquipment> obtainedUserEquipments = userEquipmentService.getUserEquipments(userId);
        assertEquals(1, obtainedUserEquipments.size());
        assertEquals(userEquipment, obtainedUserEquipments.get(0));
    }

    @Test
    public void testGetByUserId() {
        final int count = 10;
        for (int i = 0; i < count; i++) {
            createUserEquipment("user_" + i, EquipmentType.Wing, "wing_xxxx");
        }
        assertEquals(count, userEquipmentService.count());

        List<UserEquipment> obtainedUserEquipments = userEquipmentService.getUserEquipments("user_0");
        assertEquals(1, obtainedUserEquipments.size());
    }

    private UserEquipment createUserEquipment(String userId, EquipmentType equipmentType, String equipmentKey) {
        UserEquipment userEquipment = new UserEquipment();
        userEquipment.setUserId(userId);
        userEquipment.setEquipmentType(equipmentType);
        userEquipment.setEquipmentKey(equipmentKey);
        userEquipmentService.create(userEquipment);
        return userEquipment;
    }
}
