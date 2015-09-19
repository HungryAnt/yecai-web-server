package com.antsoft.yecai.mapper;

import com.antsoft.yecai.model.Account;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ant on 2015/9/16.
 */
public interface AccountMapper {
    void clear();
    void create(Account account);
    int countByUserId(String userId);
    long getAmount(String userId);
    void updateAmount(@Param("userId") String userId, @Param("amount") long amount);
}
