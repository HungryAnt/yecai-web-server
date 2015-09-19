package com.antsoft.yecai.service;

import com.antsoft.yecai.mapper.AccountMapper;
import com.antsoft.yecai.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ant on 2015/9/15.
 */
@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

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
        int count = accountMapper.countByUserId(userId);
        if (count == 0) {
            return 0;
        }
        return accountMapper.getAmount(userId);
    }
}
