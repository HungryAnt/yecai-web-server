//package com.antsoft.yecai.service.cache;
//
//import com.antsoft.yecai.YecaiTestSuite;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//
///**
// * Created by ant on 2016/5/3.
// */
//@YecaiTestSuite
//@RunWith(SpringJUnit4ClassRunner.class)
//public class RedisCacheServiceImplTest {
//    @Autowired
//    private RedisCacheServiceImpl redisCacheServiceImpl;
//
//    @Before
//    @After
//    public void clearAll() throws Exception {
//        redisCacheServiceImpl.clearAll();
//    }
//
//    @Test
//    public void testSetValue() throws Exception {
//        boolean result = redisCacheServiceImpl.setValue("key", "value");
//        assertTrue(result);
//    }
//
//    @Test
//    public void testGetValue() throws Exception {
//        final String key = "ant_key";
//        final String value = "ant_value";
//        redisCacheServiceImpl.setValue(key, value);
//        String actualValue = redisCacheServiceImpl.getValue(key);
//        assertEquals(value, actualValue);
//    }
//
//    @Test
//    public void testGetValueWithNoData() throws Exception {
//        String actualValue = redisCacheServiceImpl.getValue("key");
//        assertNull(actualValue);
//    }
//}
