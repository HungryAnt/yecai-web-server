package com.antsoft.yecai.model;

/**
 * Created by ant on 2015/11/7.
 */
public class UserLoginResult {
    private boolean success = false;
    private String userId = "";

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
