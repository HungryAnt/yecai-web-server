/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sunlin05 on 2015/1/27.
 */
public class JdbcTemplateUtility {
    public static int getIntegerValue(JdbcTemplate jdbcTemplate, String sql, Object[] args) {
        List<Integer> values = jdbcTemplate.query(sql, args, new IntegerValueRowMapper());
        return values.size() > 0 ? values.get(0) : 0;
    }

    public static long getLongValue(JdbcTemplate jdbcTemplate, String sql, Object[] args) {
        List<Long> values = jdbcTemplate.query(sql, args, new LongValueRowMapper());
        return values.size() > 0 ? values.get(0) : 0;
    }

    public static double getDoubleValue(JdbcTemplate jdbcTemplate, String sql, Object[] args) {
        List<Double> values = jdbcTemplate.query(sql, args, new DoubleValueRowMapper());
        return values.size() > 0 ? values.get(0) : 0;
    }

    private static class IntegerValueRowMapper implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getInt(1);
        }
    }

    private static class LongValueRowMapper implements RowMapper<Long> {
        @Override
        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getLong(1);
        }
    }

    private static class DoubleValueRowMapper implements RowMapper<Double> {
        @Override
        public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getDouble(1);
        }
    }
}
