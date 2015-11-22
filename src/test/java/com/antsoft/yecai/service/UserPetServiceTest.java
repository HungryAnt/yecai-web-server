package com.antsoft.yecai.service;

import com.antsoft.yecai.YecaiTestSuite;
import com.antsoft.yecai.model.UserPet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by ant on 2015/11/22.
 */
@YecaiTestSuite
@RunWith(SpringJUnit4ClassRunner.class)
public class UserPetServiceTest {
    private static final String USER_ID = "abc123";
    private static final String PET_ID = "pet_c1";

    @Autowired
    private UserPetService userPetService;

    @Before
    public void setUp() throws Exception {
        userPetService.clear();
    }

    @After
    public void tearDown() throws Exception {
        userPetService.clear();
    }

    @Test
    public void testCreate() {
        userPetService.create(USER_ID, PET_ID);
        assertEquals(1, userPetService.count());
    }

    @Test
    public void testGetByPetId() {
        userPetService.create(USER_ID, PET_ID);
        UserPet userPet = userPetService.getByPetId(PET_ID);
        assertNotNull(userPet);
        assertEquals(USER_ID, userPet.getUserId());
        assertEquals(PET_ID, userPet.getPetId());
    }

    @Test
    public void testGetPetsByUserId() {
        final int count = 10;
        final String petIdPrefix = "pet_c";
        for (int i = 0; i < count; i++) {
            String petId = petIdPrefix + i;
            userPetService.create(USER_ID, petId);
        }
        List<UserPet> userPets = userPetService.getPetsByUserId(USER_ID);
        assertEquals(count, userPets.size());

        for (UserPet userPet : userPets) {
            assertEquals(USER_ID, userPet.getUserId());
            assertTrue(userPet.getPetId().startsWith(petIdPrefix));
        }
    }
}
