package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.GoodsPaymentRecordMapper;
import com.antsoft.yecai.mapper.RubbishRecycleRecordMapper;
import com.antsoft.yecai.model.GoodsPaymentRecord;
import com.antsoft.yecai.model.RubbishRecycleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ant on 2015/10/1.
 */
@Service
public class RecordService {
    @Autowired
    private GoodsPaymentRecordMapper goodsPaymentRecordMapper;

    @Autowired
    private RubbishRecycleRecordMapper rubbishRecycleRecordMapper;

    public void createGoodsPaymentRecord(String userId, String goodsKey, long amount) {
        GoodsPaymentRecord record = new GoodsPaymentRecord();
        record.setUserId(userId);
        record.setGoodsKey(goodsKey);
        record.setAmount(amount);
        goodsPaymentRecordMapper.create(record);
    }

    public void createRubbishRecycleRecord(String userId, int rubbishCount, long amount) {
        RubbishRecycleRecord record = new RubbishRecycleRecord();
        record.setUserId(userId);
        record.setRubbishCount(rubbishCount);
        record.setAmount(amount);
        rubbishRecycleRecordMapper.create(record);
    }
}
