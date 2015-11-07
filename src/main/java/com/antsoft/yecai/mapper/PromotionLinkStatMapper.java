package com.antsoft.yecai.mapper;

/**
 * Created by ant on 2015/11/7.
 */
public interface PromotionLinkStatMapper {
    void create(String name);
    Long getPromotionCountByName(String name);
    void increaseCount(String name);
}
