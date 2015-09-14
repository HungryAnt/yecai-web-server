/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.configuration;

import ch.qos.logback.access.servlet.TeeFilter;
import ch.qos.logback.access.tomcat.LogbackValve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Configuration
@ConditionalOnExpression("${logging.has_access_log:true}")
class AccessLogConfiguration {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext context;

    @Value("${logging.access-config:classpath:logback-access.xml}")
    String filename;

    @Bean
    @ConditionalOnExpression("${logging.access_debug_log_full_content:true}")
    public FilterRegistrationBean teeFilter() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        // TeeFilter used to log requestContent & responseContent
        filterRegBean.setFilter(new TeeFilter());
        return filterRegBean;
    }

    @Bean
    EmbeddedServletContainerCustomizer embeddedServletContainerCustomizerAccessLog() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer factory) {

                final String configFilePath;
                try {
                    // 由于logback-access的logbackValve只能读真实文件，所以将resource复制到临时文件，用于初始化
                    String originalUrl = context.getResource(filename).getURL().toString();
                    log.debug("logback-access originalUrl: {}", originalUrl);

                    String fileSignature =
                            Integer.toString(originalUrl.hashCode()) + context.getResource(filename).contentLength();
                    Path path = Paths.get(System.getProperty("java.io.tmpdir"),
                                                 "antsoft-logback-access-" + fileSignature + ".xml");
                    configFilePath = path.toString();
                    log.debug("logback-access configFilePath: {}", configFilePath);

                    log.debug("copy logback-access.xml begin");
                    try (InputStream inputStream = context.getResource(filename).getInputStream()) {
                        Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
                    }
                    log.debug("copy logback-access.xml success");
                } catch (Exception e) {
                    log.error("logback-access config error", e);
                    return;
                }

                // tomcat server
                if (factory instanceof TomcatEmbeddedServletContainerFactory) {
                    TomcatEmbeddedServletContainerFactory tomcatFactory =
                            (TomcatEmbeddedServletContainerFactory) factory;

                    LogbackValve logbackValve = new LogbackValve();
                    logbackValve.setFilename(configFilePath);
                    tomcatFactory.addContextValves(logbackValve);
                }
            }
        };
    }
}