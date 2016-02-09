package com.antsoft.yecai.mapper;

import com.antsoft.yecai.YecaiTestSuite;
import com.antsoft.yecai.model.DailyRedPacket;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by ant on 2016/2/10.
 */
@YecaiTestSuite
@RunWith(SpringJUnit4ClassRunner.class)
public class DailyRedPacketMapperTest {
    @Autowired
    private DailyRedPacketMapper dailyRedPacketMapper;

    @Before
    @After
    public void clear() throws Exception {
        dailyRedPacketMapper.clear();
    }

    @Test
    public void testCreate() throws Exception {
        DailyRedPacket dailyRedPacket = new DailyRedPacket() {
            {
                setUserId("userId");
                setAmount(100);
                setCtime(DateTime.parse("2016-01-01T00:00:00Z"));
            }
        };
        dailyRedPacketMapper.create(dailyRedPacket);
        assertEquals(dailyRedPacket, dailyRedPacketMapper.get(dailyRedPacket.getId()));
    }
}
