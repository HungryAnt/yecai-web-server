package com.antsoft.yecai.mapper;

import com.antsoft.yecai.model.DailyRedPacket;

/**
 * Created by ant on 2016/2/9.
 */
public interface DailyRedPacketMapper {
    int count();
    void clear();
    void create(DailyRedPacket dailyRedPacket);
    DailyRedPacket get(long id);
}
