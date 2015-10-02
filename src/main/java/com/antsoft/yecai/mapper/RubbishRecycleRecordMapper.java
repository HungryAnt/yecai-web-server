package com.antsoft.yecai.mapper;

import com.antsoft.yecai.model.RubbishRecycleRecord;

/**
 * Created by ant on 2015/10/1.
 */
public interface RubbishRecycleRecordMapper {
    void clear();
    int count();
    void create(RubbishRecycleRecord record);
}
