/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.backendexception;

import org.springframework.http.HttpStatus;

/**
 * Created by sunlin05 on 2014/12/10.
 * 后端异常基类（Ant将定义所有后端异常）
 */
public class BackendException extends RuntimeException {
    private HttpStatus httpStatus = HttpStatus.OK;
    private String code;

    protected BackendException(HttpStatus httpStatus, String code, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
    }

//    protected BackendException(HttpStatus httpStatus, String code, String message, Throwable cause) {
//        super(message, cause);
//        this.httpStatus = httpStatus;
//        this.code = code;
//    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }
}
