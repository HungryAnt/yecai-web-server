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

import java.util.List;

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
        Goods goods1 = new Goods() {
            {
                setEquipmentType(EquipmentType.Wing);
                setKey("wing_1");
                setPrice(100);
            }
        };
        goodsMapper.create(goods1);

        assertEquals(1, goodsMapper.count());
        assertEquals(1, goodsMapper.countByEquipmentType(EquipmentType.Wing));
        assertEquals(0, goodsMapper.countByEquipmentType(EquipmentType.EarWear));

        Goods goods2 = goodsMapper.getByKey(goods1.getKey());
        assertEquals(goods1, goods2);
    }

    @Test
    public void testGetByEquipmentType() throws Exception {
        final int count = 100;
        final EquipmentType equipmentType = EquipmentType.Wing;
        final long price = 9999;
        for (int i = 0; i < count; i++) {
            final String key = "e_" + i;
            Goods goods = new Goods() {
                {
                    setEquipmentType(equipmentType);
                    setKey(key);
                    setPrice(price);
                }
            };
            goodsMapper.create(goods);
        }
        List<Goods> goodsList = goodsMapper.getByEquipmentType(equipmentType, 1, count);
        assertEquals(count, goodsList.size());
        for (Goods goods : goodsList) {
            assertEquals(equipmentType, goods.getEquipmentType());
            assertEquals(price, goods.getPrice());
        }
    }
}
