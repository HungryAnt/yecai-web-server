/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.backendexception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/**
 * Created by sunlin05 on 2014/12/10.
 */
public class BackendErrorResponse {
    private String requestId;
    private String code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> fieldErrorMap;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getFieldErrorMap() {
        return fieldErrorMap;
    }

    public void setFieldErrorMap(Map<String, String> fieldErrorMap) {
        this.fieldErrorMap = fieldErrorMap;
    }
}
