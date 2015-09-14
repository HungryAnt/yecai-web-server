/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.TimeZone;

/**
* 设置系统时钟为UTC时间，保证Date序列化、反序列化时间正确
*/
@Configuration
@ConditionalOnExpression("false")
public class UTCTimeZoneConfiguration implements ServletContextListener{
    public void contextInitialized(ServletContextEvent event) {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public void contextDestroyed(ServletContextEvent event) {}
}
