/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by sunlin05 on 2014/12/11.
 */
@Configuration
public class AntWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Value("${needValidateSignature:true}")
    private boolean needValidateSignature;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestHandlerInterceptor())
                .addPathPatterns("/**");
    }
}
