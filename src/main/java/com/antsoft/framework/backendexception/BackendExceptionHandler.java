/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.backendexception;

import com.antsoft.framework.utils.RequestUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by sunlin05 on 2014/12/10.
 */
@ControllerAdvice
public class BackendExceptionHandler {
    private Logger log = LoggerFactory.getLogger(getClass());

    private static BackendErrorResponse getErrorResponse(HttpServletRequest request, HttpServletResponse response,
                                                         String code, String message) {
        BackendErrorResponse errorResponse = new BackendErrorResponse();
        String requestId = RequestUtility.getRequestId(request, response);
        if (requestId == null) {
            requestId = "";
        }
        errorResponse.setRequestId(requestId);
        errorResponse.setCode(code);
        errorResponse.setMessage(message);
        return errorResponse;
    }

    private static String errorResponseToString(BackendErrorResponse errorResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ErrorResponse: requestId:").append(errorResponse.getRequestId())
                .append(" code").append(errorResponse.getCode()).append("\n");
        Map<String, String> map = errorResponse.getFieldErrorMap();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                stringBuilder.append(" ").append(entry.getKey()).append(":").append(entry.getValue());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @ExceptionHandler(RequestBodyValidationException.class)
    public ResponseEntity<BackendErrorResponse> handleRequestBodyValidationException(HttpServletRequest request,
                                                                                     HttpServletResponse response,
                                                                                     RequestBodyValidationException
                                                                                             ex) {
        log.warn("Handle RequestBodyValidationException:", ex);
        BackendErrorResponse errorResponse = getErrorResponse(request, response, ex.getCode(), ex.getMessage());
        errorResponse.setFieldErrorMap(ex.getFieldMassageMap());
        log.warn(errorResponseToString(errorResponse));
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(BackendException.class)
    public ResponseEntity<BackendErrorResponse> handleBackendException(HttpServletRequest request,
                                                                       HttpServletResponse response,
                                                                       BackendException ex) {
        log.warn("Handle BackendException:", ex);
        BackendErrorResponse errorResponse = getErrorResponse(request, response, ex.getCode(), ex.getMessage());
        log.warn(errorResponseToString(errorResponse));
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<BackendErrorResponse> handleDataAccessException(HttpServletRequest request,
                                                                          HttpServletResponse response,
                                                                          DataAccessException ex) {
        log.error("Handle DataAccessException:", ex);
        BackendErrorResponse errorResponse = getErrorResponse(request, response, "DatabaseError", "数据库错误");
        log.error(errorResponseToString(errorResponse));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BackendErrorResponse> handleException(HttpServletRequest request,
                                                                HttpServletResponse response, Exception ex) {
        log.error("Handle Exception:", ex);
        BackendErrorResponse errorResponse = getErrorResponse(request, response, "UnknownError", ex.getMessage());
        log.error(errorResponseToString(errorResponse));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
