package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.AccountMapper;
import com.antsoft.yecai.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ant on 2015/9/15.
 */
@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RecordService recordService;

    public void clear() {
        accountMapper.clear();
    }

    public void updateAmount(String userId, long amount) {
        if (accountMapper.countByUserId(userId) == 0) {
            Account account = new Account();
            account.setUserId(userId);
            account.setAmount(amount);
            accountMapper.create(account);
        } else {
            accountMapper.updateAmount(userId, amount);
        }
    }

    public long getAmount(String userId) {
        if (accountMapper.countByUserId(userId) == 0) {
            return 0;
        }
        return accountMapper.getAmount(userId);
    }

    public long getAmountForUpdate(String userId) {
        if (accountMapper.countByUserId(userId) == 0) {
            return 0;
        }
        return accountMapper.getAmountForUpdate(userId);
    }

    public void decreaseAmount(String userId, long amount) {
        accountMapper.decreaseAmount(userId, amount);
    }

    public void increaseAmount(String userId, long amount) {
        accountMapper.increaseAmount(userId, amount);
    }

    @Transactional
    public void recharge(String userId, long amount) {
        increaseAmount(userId, amount);
        recordService.createRechargeRecord(userId, amount);
    }
}
