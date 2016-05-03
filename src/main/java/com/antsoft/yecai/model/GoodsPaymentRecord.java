package com.antsoft.yecai.model;

import lombok.Data;

/**
 * Created by ant on 2015/10/1.
 */
@Data
public class GoodsPaymentRecord {
    private String userId;
    private String goodsKey;
    private long amount;
}
