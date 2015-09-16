package com.antsoft.yecai;

import com.antsoft.Application;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sunlin05 on 2015/3/2.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
@IntegrationTest("server.port=0")
@ActiveProfiles("test")
public @interface YecaiTestSuite {
}
