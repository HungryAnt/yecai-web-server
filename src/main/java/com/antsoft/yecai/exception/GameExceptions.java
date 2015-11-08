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

    public static class UserIdAlreadyExistsException extends BackendException {
        public UserIdAlreadyExistsException() {
            super(HttpStatus.BAD_REQUEST, "UserIdAlreadyExists",
                    String.format("UserId已被绑定"));
        }
    }

    public static class SignNotMatchException extends BackendException {
        public SignNotMatchException() {
            super(HttpStatus.BAD_REQUEST, "SignNotMatch",
                    String.format("签名失败"));
        }
    }
}
