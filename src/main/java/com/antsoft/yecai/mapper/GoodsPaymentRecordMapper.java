package com.antsoft.yecai.mapper;

import com.antsoft.yecai.model.GoodsPaymentRecord;

/**
 * Created by ant on 2015/10/1.
 */
public interface GoodsPaymentRecordMapper {
    void clear();
    int count();
    void create(GoodsPaymentRecord record);
}
