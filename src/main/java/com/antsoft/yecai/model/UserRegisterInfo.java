package com.antsoft.yecai.model;

import com.antsoft.yecai.type.Gender;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by ant on 2015/10/18.
 */
public class UserRegisterInfo {
    @NotBlank
    @Size(min = 3, max = 16)
    private String loginName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

    private String relatedUserId;

    private String userId;

    private Gender gender = Gender.Unknown;

    @NotNull
    @Size(min = 0, max = 32)
    private String qq;

    @NotNull
    @Size(min = 0, max = 64)
    private String email;

    @NotNull
    @Size(min = 0, max = 256)
    private String introduction;

    private String sign;

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

    public String getRelatedUserId() {
        return relatedUserId;
    }

    public void setRelatedUserId(String relatedUserId) {
        this.relatedUserId = relatedUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
