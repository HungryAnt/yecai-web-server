package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.RubbishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ant on 2015/10/1.
 */
@Service
public class RubbishService {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RubbishMapper rubbishMapper;

    @Autowired
    private RecordService recordService;

    public void create() {

    }

    @Transactional
    public void recycle(String userId) {
        int rubbishCount = rubbishMapper.getAllCountForUpdate(userId);
        rubbishMapper.clearAllCount(userId);
        accountService.increaseAmount(userId, rubbishCount);
        recordService.createRubbishRecycleRecord(userId, rubbishCount, (long) rubbishCount);
    }
}
