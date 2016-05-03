package com.antsoft.yecai.model;

import com.antsoft.yecai.type.Gender;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by ant on 2015/10/18.
 */
@Data
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
}
