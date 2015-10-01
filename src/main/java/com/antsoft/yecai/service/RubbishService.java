package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.RubbishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ant on 2015/10/1.
 */
@Service
public class RubbishService {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RubbishMapper rubbishMapper;

    public void create() {

    }

    public void recycle() {

    }
}
