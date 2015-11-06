package com.antsoft.yecai.exception;

import com.antsoft.framework.backendexception.BackendException;
import org.springframework.http.HttpStatus;

/**
 * Created by ant on 2015/11/6.
 */
public class GameExceptions {
    public static class LoginUserAlreadyExistsException extends BackendException {
        public LoginUserAlreadyExistsException(String loginUser) {
            super(HttpStatus.BAD_REQUEST, "LoginUserAlreadyExists",
                    String.format("该用户名(%s)已存在", loginUser));
        }
    }
}
