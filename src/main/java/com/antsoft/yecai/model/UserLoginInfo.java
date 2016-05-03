package com.antsoft.yecai.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by ant on 2015/10/18.
 */
@Data
public class UserLoginInfo {
    @NotBlank
    @Size(min = 3, max = 16)
    private String loginName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

    private String sign;
}
