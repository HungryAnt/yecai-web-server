/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.utils;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by sunlin05 on 2015/1/29.
 */
public class DataSourceUtility {
    public static DataSource getEmbeddedH2DataSource(String ... scripts) {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2).setScriptEncoding("utf8").addScript("classpath:db/h2_init.sql");;
        for (String script : scripts) {
            embeddedDatabaseBuilder.addScript(script);
        }
        return embeddedDatabaseBuilder.build();
    }

    public static DataSource getEmbeddedHsqlDataSource(String ... scripts) {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL).setScriptEncoding("utf8").addScript("classpath:db/hsql_init.sql");
        for (String script : scripts) {
            embeddedDatabaseBuilder.addScript(script);
        }
        return embeddedDatabaseBuilder.build();
    }

    public static DataSource getTomcatPoolingDataSource(String databaseUrl, String userName, String password) {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        dataSource.setInitialSize(5);   // 连接池启动时创建的初始化连接数量（默认值为0）
        dataSource.setMaxActive(20);    // 连接池中可同时连接的最大的连接数
        dataSource.setMaxIdle(12);      // 连接池中最大的空闲的连接数，超过的空闲连接将被释放，如果设置为负数表示不限
        dataSource.setMinIdle(0);       // 连接池中最小的空闲的连接数，低于这个数量会被创建新的连接
        dataSource.setMaxWait(60000);   // 最大等待时间，当没有可用连接时，连接池等待连接释放的最大时间，超过该时间限制会抛出异常，如果设置-1表示无限等待
        dataSource.setRemoveAbandonedTimeout(180);  // 超过时间限制，回收没有用(废弃)的连接
        dataSource.setRemoveAbandoned(true);        // 超过removeAbandonedTimeout时间后，是否进 行没用连接（废弃）的回收
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30); // 检查无效连接的时间间隔 设为30分钟
        return dataSource;
    }

    public static DataSource getMySqlDataSource(String databaseUrl, String userName, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }
}
