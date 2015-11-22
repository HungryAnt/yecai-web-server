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
    private static final String PET_TYPE = "pet_c1";

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
        userPetService.create(USER_ID, PET_TYPE);
        assertEquals(1, userPetService.count());
    }

    @Test
    public void testGetByPetId() {
        UserPet userPet = userPetService.create(USER_ID, PET_TYPE);
        UserPet resultUserPet = userPetService.getByPetId(userPet.getPetId());
        assertNotNull(resultUserPet);
        assertEquals(USER_ID, resultUserPet.getUserId());
        assertEquals(PET_TYPE, resultUserPet.getPetType());
        assertEquals(userPet.getPetId(), resultUserPet.getPetId());
    }

    @Test
    public void testGetPetsByUserId() {
        final int count = 10;
        final String petTypePrefix = "pet_c";
        for (int i = 0; i < count; i++) {
            String petType = petTypePrefix + i;
            userPetService.create(USER_ID, petType);
        }
        List<UserPet> userPets = userPetService.getPetsByUserId(USER_ID);
        assertEquals(count, userPets.size());

        for (UserPet userPet : userPets) {
            assertEquals(USER_ID, userPet.getUserId());
            assertTrue(userPet.getPetType().startsWith(petTypePrefix));
        }
    }
}
