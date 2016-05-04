package com.antsoft.yecai.model;

import lombok.Data;

/**
 * Created by ant on 2015/11/22.
 */
@Data
public class UserPet {
    private long id;
    private String petId;
    private String petType;
    private String userId;
    private Level level;
}
