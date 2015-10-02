package com.antsoft.yecai.mapper;

import com.antsoft.yecai.model.RechargeRecord;

/**
 * Created by ant on 2015/10/2.
 */
public interface RechargeRecordMapper {
    void clear();
    int count();
    void create(RechargeRecord record);
}
