/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.configuration;

import com.antsoft.framework.utils.AntConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by sunlin05 on 2014/12/11.
 */
public class RequestHandlerInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());

    // 记录访问时间，用于性能调试
    private ThreadLocal<Long> beginTime = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestId = request.getHeader(AntConstant.X_REQUEST_ID);
        if (requestId == null) {
            requestId = "";
        }
        if (StringUtils.isEmpty(requestId)) {
            requestId = UUID.randomUUID().toString();
        }
        response.addHeader(AntConstant.X_REQUEST_ID, requestId);

        // 记录访问时间
        beginTime.set(System.currentTimeMillis());
        logger.info("[begin] {} {}", request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long timeUsed = System.currentTimeMillis() - beginTime.get();
        logger.info("[status:{},time:{}ms] {} {}", response.getStatus(), timeUsed, request.getMethod(),
                           request.getRequestURI());
    }
}
