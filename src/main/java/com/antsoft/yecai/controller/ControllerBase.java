package com.antsoft.yecai.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Created by sunlin05 on 2015/4/19.
 */
public class ControllerBase {
    protected String getErrorMessage(BindingResult bindingResult) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                stringBuilder.append(String.format("Field:%s ErrorMessage:%s",
                                                          fieldError.getObjectName(),
                                                          fieldError.getDefaultMessage()));
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
