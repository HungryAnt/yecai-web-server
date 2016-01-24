package com.antsoft.yecai.mapper;

import com.antsoft.yecai.YecaiTestSuite;
import com.antsoft.yecai.model.Goods;
import com.antsoft.yecai.type.EquipmentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by ant on 2016/1/24.
 */
@YecaiTestSuite
@RunWith(SpringJUnit4ClassRunner.class)
public class GoodsMapperTest {
    @Autowired
    private GoodsMapper goodsMapper;

    @Before
    public void setUp() throws Exception {
        goodsMapper.clear();
    }

    @After
    public void tearDown() throws Exception {
        goodsMapper.clear();
    }

    @Test
    public void testCreate() throws Exception {
        Goods goods1 = new Goods();
        goods1.setEquipmentType(EquipmentType.Wing);
        goods1.setKey("wing_1");
        goods1.setPrice(100);
        goodsMapper.create(goods1);

        Goods goods2 = goodsMapper.getByKey(goods1.getKey());
        assertEquals(goods1, goods2);
    }
}
