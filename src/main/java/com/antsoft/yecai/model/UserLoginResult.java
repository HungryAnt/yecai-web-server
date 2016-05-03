package com.antsoft.yecai.model;

import lombok.Data;

/**
 * Created by ant on 2015/11/7.
 */
@Data
public class UserLoginResult {
    private boolean success = false;
    private String userId = "";
}
