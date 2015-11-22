/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.yecai.configuration;

import com.antsoft.framework.datetime.DateTimeTypeHandler;
import com.antsoft.framework.utils.DataSourceUtility;
import com.antsoft.yecai.mapper.*;
import com.antsoft.yecai.model.User;
import com.antsoft.yecai.model.UserPet;
import com.antsoft.yecai.model.UserRegisterInfo;
import com.antsoft.yecai.model.UserVehicle;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.EnumTypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by sunlin05 on 2015/3/29.
 */
@Configuration
public class DatabaseConfiguration {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${yecai.database.isEmbedded:false}")
    private Boolean databaseIsEmbedded;

    @Value("${yecai.database.url}")
    private String databaseUrl;

    @Value("${yecai.database.username}")
    private String databaseUsername;

    @Value("${yecai.database.password}")
    private String databasePassword;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource;
        if (databaseIsEmbedded) {
            dataSource = DataSourceUtility.getEmbeddedH2DataSource("classpath:db/yecai_h2.sql");
        } else {
            dataSource = DataSourceUtility.getTomcatPoolingDataSource(
                    databaseUrl, databaseUsername, databasePassword);
        }
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        Class[] types = new Class[] {
                DateTimeTypeHandler.class,
                EnumTypeHandler.class,
                UserVehicle.class, UserPet.class,
                User.class,
                UserRegisterInfo.class,
        };

        sqlSessionFactoryBean.setTypeAliases(types);
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

    private <T> MapperFactoryBean getMapper(Class<T> mapperInterface) {
        MapperFactoryBean<T> mapperFactoryBean = new MapperFactoryBean<T>();
        try {
            mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory());
            mapperFactoryBean.setMapperInterface(mapperInterface);
        } catch (Exception ex) {
            logger.error("error when create mapper: ", ex);
            throw new RuntimeException(ex);
        }
        return mapperFactoryBean;
    }

    @Bean
    public MapperFactoryBean userMapper() {
        return getMapper(UserMapper.class);
    }

    @Bean
    public MapperFactoryBean userVehicleMapper() {
        return getMapper(UserVehicleMapper.class);
    }

    @Bean
    public MapperFactoryBean accountMapper() {
        return getMapper(AccountMapper.class);
    }

    @Bean
    public MapperFactoryBean cheatingUserMapper() {
        return getMapper(CheatingUserMapper.class);
    }

    @Bean
    public MapperFactoryBean rubbishMapper() {
        return getMapper(RubbishMapper.class);
    }

    @Bean
    public MapperFactoryBean goodsPaymentRecordMapper() {
        return getMapper(GoodsPaymentRecordMapper.class);
    }

    @Bean
    public MapperFactoryBean rubbishRecycleRecordMapper() {
        return getMapper(RubbishRecycleRecordMapper.class);
    }

    @Bean
    public MapperFactoryBean rechargeRecordMapper() {
        return getMapper(RechargeRecordMapper.class);
    }

    @Bean
    public MapperFactoryBean userRegisterInfoMapper() {
        return getMapper(UserRegisterInfoMapper.class);
    }

    @Bean
    public MapperFactoryBean promotionLinkStatMapper() {
        return getMapper(PromotionLinkStatMapper.class);
    }

    @Bean
    public MapperFactoryBean userPetMapper() {
        return getMapper(UserPetMapper.class);
    }
}
