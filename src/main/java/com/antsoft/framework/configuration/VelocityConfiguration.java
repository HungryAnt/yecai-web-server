package com.antsoft.framework.configuration;///*
// * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
// */
//package com.antsoft.framework.configuration;
//
//import java.util.Properties;
//
//import javax.servlet.Servlet;
//
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
//import org.springframework.boot.bind.RelaxedPropertyResolver;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.env.Environment;
//import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
//import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
//
///**
// * Created by shiziye on 2014/6/7.
// * TODO: shiziye : remove this class when spring-boot-starter-velocity 1.1.0 ready
// */
//@Configuration
//@ConditionalOnClass({Servlet.class})
//@AutoConfigureAfter(WebMvcAutoConfiguration.class)
//class VelocityConfiguration implements EnvironmentAware {
//
//    private RelaxedPropertyResolver environment;
//
//    @Override
//    public void setEnvironment(Environment environment) {
//        this.environment = new RelaxedPropertyResolver(environment,
//                "spring.velocity.");
//    }
//
//    @Bean
//    VelocityConfigurer velocityConfig() {
//        VelocityConfigurer config = new VelocityConfigurer();
//        Properties props = new Properties();
//        props.setProperty("input.encoding", "UTF-8");
//        props.setProperty("output.encoding", "UTF-8");
//        config.setVelocityProperties(props);
//        return config;
//    }
//
//    /*
//    // need spring-security-taglibs, don't need security now
//    @Bean
//    Authz authz() {return new AuthzImpl();}
//    */
//
//    @Bean
//    VelocityViewResolver velocityViewResolver() {
//        VelocityViewResolver resolver = new VelocityViewResolver();
//        resolver.setSuffix(this.environment.getProperty("suffix", ".html"/*".vm"*/));
//        resolver.setPrefix(this.environment.getProperty("prefix", "/templates/"));
//        resolver.setCache(Boolean.getBoolean(this.environment.getProperty("cache", "true")));
//        resolver.setRequestContextAttribute(this.environment.getProperty("requestContext", "requestContext"));
//        resolver.setNumberToolAttribute(this.environment.getProperty("numberTool", "numberTool"));
//        resolver.setDateToolAttribute(this.environment.getProperty("dateTool", "dateTool"));
//        resolver.setContentType("text/html;charset=UTF-8");
//
//        /*
//        // need spring-security-taglibs, don't need security now
//        // set velocity authentication variable
//        Properties properties = new Properties();
//        properties.put("authz", authz());
//        resolver.setAttributes(properties);
//        */
//
//        // Needs to come before any fallback resolver (e.g. a
//        // InternalResourceViewResolver)
//        resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 20);
//        return resolver;
//    }
//}
