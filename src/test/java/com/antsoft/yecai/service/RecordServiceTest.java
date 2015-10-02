package com.antsoft.yecai.service;

import com.antsoft.yecai.YecaiTestSuite;
import com.antsoft.yecai.mapper.GoodsPaymentRecordMapper;
import com.antsoft.yecai.mapper.RubbishRecycleRecordMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by ant on 2015/10/2.
 */
@YecaiTestSuite
@RunWith(SpringJUnit4ClassRunner.class)
public class RecordServiceTest {
    @Autowired
    private GoodsPaymentRecordMapper goodsPaymentRecordMapper;

    @Autowired
    private RubbishRecycleRecordMapper rubbishRecycleRecordMapper;

    @Autowired
    private RecordService recordService;

    @Before
    public void setUp() {
        clearAll();
    }

    @After
    public void tearDown() {
        clearAll();
    }

    private void clearAll() {
        goodsPaymentRecordMapper.clear();
        rubbishRecycleRecordMapper.clear();
    }

    @Test
    public void testCreateGoodsPaymentRecord() {
        final int count = 10;
        assertEquals(0, goodsPaymentRecordMapper.count());
        for (int i = 0; i < count; i++) {
            recordService.createGoodsPaymentRecord("user_1", "goods_key", 100);
        }
        assertEquals(count, goodsPaymentRecordMapper.count());
    }

    @Test
    public void testCreateRubbishRecycleRecord() {
        final int count = 10;
        assertEquals(0, rubbishRecycleRecordMapper.count());
        for (int i = 0; i < count; i++) {
            recordService.createRubbishRecycleRecord("user_1", 100, 100);
        }
        assertEquals(count, rubbishRecycleRecordMapper.count());
    }
}
