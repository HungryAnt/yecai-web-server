package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.CheatingUserMapper;
import com.antsoft.yecai.model.TimestampPair;
import com.antsoft.yecai.utils.DecimalUtility;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ant on 2015/9/30.
 */
@Service
public class AntiCheatingService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CheatingUserMapper cheatingUserMapper;

    private Map<String, TimestampPair> userClientTimestampMap = new HashMap<>();

    public void initClientTimestamp(String userId, long clientTimestampInS) {
        updateTimestamp(userId, clientTimestampInS, getServerTimestampInS());
    }

    public boolean checkCheating(String userId, long clientTimestampInS) {
        TimestampPair timestampPair = userClientTimestampMap.get(userId);
        long serverTimestampInS = getServerTimestampInS();

        long clientDiff = clientTimestampInS - timestampPair.getClientTimestampInS();
        if (clientDiff <= 0) {
            throw new RuntimeException("wrong clientDiff, current: " + clientTimestampInS
                    + "last: " + timestampPair.getClientTimestampInS());
        }

        long serverDiff = getServerTimestampInS() - timestampPair.getServerTimestampIns();
        if (serverDiff < 10) { // 小于10秒，检测意义不大，忽略
            return false;
        }

        if (clientDiff <= serverDiff) {
            return false;
        }

        BigDecimal speedUpRate = DecimalUtility.toDecimal(
                (clientDiff - serverDiff) * 1.0 / serverDiff);

        if (speedUpRate.compareTo(new BigDecimal("0.2")) >= 0) {
            // 检测到作弊，加速超过20%
            cheatingUserMapper.add(userId, speedUpRate);
            return true;
        }

        updateTimestamp(userId, clientTimestampInS, serverTimestampInS);
        return false;
    }

    private void updateTimestamp(String userId, long clientTimestampInS, long serverTimestampInS) {
        TimestampPair timestampPair = new TimestampPair();
        timestampPair.setClientTimestampInS(clientTimestampInS);
        timestampPair.setServerTimestampIns(serverTimestampInS);
        userClientTimestampMap.put(userId, timestampPair);
    }

    private long getServerTimestampInS() {
        return DateTime.now().getMillis() / 1000;
    }
}
