package com.antsoft.yecai.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by ant on 2015/10/18.
 */
public class UserLoginInfo {
    @NotBlank
    @Size(min = 3, max = 12)
    private String loginName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
