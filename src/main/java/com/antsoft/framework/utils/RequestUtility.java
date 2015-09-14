/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */

package com.antsoft.framework.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestUtility {
    /**
     * 获取requestId
     * @param request
     * @param response
     * @return
     */
    public static String getRequestId(HttpServletRequest request, HttpServletResponse response) {
        String requestId = request.getHeader(AntConstant.X_REQUEST_ID);
        if (StringUtils.isEmpty(requestId)) {
            requestId = response.getHeader(AntConstant.X_REQUEST_ID);
        }
        return requestId;
    }
}
