package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.DailyRedPacketMapper;
import com.antsoft.yecai.model.DailyRedPacket;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ant on 2016/2/9.
 */
@Service
public class RedPacketService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DailyRedPacketMapper dailyRedPacketMapper;

    @Autowired
    private AccountService accountService;

    @Transactional
    public void obtainDailyRedPacket(String userId) {
        if (DateTime.now().isAfter(DateTime.parse("2016-02-22T00:00:00Z"))) {
            throw new RuntimeException("obtainDailyRedPacket time error");
        }

        final long money = 200;
        DateTime today = DateTime.now().withZone(DateTimeZone.forOffsetHours(8)).withTime(0, 0, 0, 0);

        DailyRedPacket dailyRedPacket = new DailyRedPacket();
        dailyRedPacket.setUserId(userId);
        dailyRedPacket.setAmount(money);
        dailyRedPacket.setCtime(today);

        try {
            dailyRedPacketMapper.create(dailyRedPacket);
        } catch (DuplicateKeyException ex) {
            logger.info("daily red packet already exists, userId:{} ctime:{}", userId, today);
            throw new RuntimeException("daily red packet already exists");
        }

        accountService.increaseAmount(userId, money);
    }
}
