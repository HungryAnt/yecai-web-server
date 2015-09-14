/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.backendexception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by sunlin05 on 2014/7/7.
 * Updated by sunlin05 on 2014/12/10.
 */
public class RequestBodyValidationException extends CommonBackendExceptions.InappropriateJSONException {
    private Map<String, String> fieldMessageMap;

    public RequestBodyValidationException() {
        fieldMessageMap = new LinkedHashMap<>();
    }

    public RequestBodyValidationException(BindingResult bindingResult) {
        this();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            fieldMessageMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    public RequestBodyValidationException(Map<String, String> errorMap) {
        this();
        for (Map.Entry<String, String> entry : errorMap.entrySet()) {
            fieldMessageMap.put(entry.getKey(), entry.getValue());
        }
    }

    public void addFieldErrorMessage(String filedName, String errorMessage) {
        fieldMessageMap.put(filedName, errorMessage);
    }

    public Map<String, String> getFieldMassageMap() {
        return fieldMessageMap;
    }
}
