package com.antsoft.framework.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by sunlin05 on 2015/4/11.
 */
@Configuration
//@ConditionalOnClass({Servlet.class})
//@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class JspConfiguration {
    @Bean
    InternalResourceViewResolver internalResourceViewResolver () {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
