/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.backendexception;

import org.springframework.http.HttpStatus;

/**
 * Created by sunlin05 on 2014/12/10.
 * 统一定义后端服务common exceptions ~Ant
 */
public class CommonBackendExceptions {
    /**
     * 无权限访问对应的资源
     */
    public static class AccessDeniedException extends BackendException {
        public AccessDeniedException() {
            super(HttpStatus.FORBIDDEN, "AccessDenied", "Access denied.");
        }
    }

    /**
     * 请求中的JSON格式正确，但语义上不符合要求。如缺少某个必需项，或者值类型不匹配等。出于兼容性考虑，对于所有无法识别的项应直接忽略，不应该返回这个错误。
     */
    public static class InappropriateJSONException extends BackendException {
        public InappropriateJSONException() {
            super(HttpStatus.BAD_REQUEST, "InappropriateJSON",
                         "The JSON you provided was well-formed and valid, but not appropriate for this operation.");
        }
    }

    /**
     * 所有未定义的其他错误。在有明确对应的其他类型的错误时（包括通用的和服务自定义的）不应该使用。
     */
    public static class InternalErrorException extends BackendException {
        public InternalErrorException() {
            super(HttpStatus.INTERNAL_SERVER_ERROR, "InternalError",
                         "We encountered an internal error. Please try again.");
        }
    }

    /**
     * Access Key ID不存在
     */
    public static class InvalidAccessKeyIdException extends BackendException {
        public InvalidAccessKeyIdException() {
            super(HttpStatus.FORBIDDEN, "InvalidAccessKeyId",
                         "The Access Key ID you provided does not exist in our records.");
        }
    }

    /**
     * Authorization头域格式错误
     */
    public static class InvalidHTTPAuthHeaderException extends BackendException {
        public InvalidHTTPAuthHeaderException() {
            super(HttpStatus.BAD_REQUEST, "InvalidHTTPAuthHeader",
                         "The HTTP authorization header is invalid. Consult the service documentation for details.");
        }
    }

    /**
     * HTTP body格式错误。例如不符合指定的Encoding等
     */
    public static class InvalidHTTPRequestException extends BackendException {
        public InvalidHTTPRequestException() {
            super(HttpStatus.BAD_REQUEST, "InvalidHTTPRequest", "There was an error in the body of your HTTP request.");
        }
    }

    /**
     * URI形式不正确。例如一些服务定义的关键词不匹配等。对于ID不匹配等问题，应定义更加具体的错误码，例如NoSuchKey。
     */
    public static class InvalidURIException extends BackendException {
        public InvalidURIException() {
            super(HttpStatus.BAD_REQUEST, "InvalidURI", "Could not parse the specified URI.");
        }
    }

    /**
     * JSON格式不合法
     */
    public static class MalformedJSONException extends BackendException {
        public MalformedJSONException() {
            super(HttpStatus.BAD_REQUEST, "MalformedJSON", "The JSON you provided was not well-formed.");
        }
    }

    /**
     * URI的版本号不合法
     */
    public static class InvalidVersionException extends BackendException {
        public InvalidVersionException() {
            super(HttpStatus.NOT_FOUND, "InvalidVersion", "The API version specified was invalid.");
        }
    }

    /**
     * 没有开通对应的服务
     */
    public static class OptInRequiredException extends BackendException {
        public OptInRequiredException() {
            super(HttpStatus.FORBIDDEN, "OptInRequired", "A subscription for the service is required.");
        }
    }

    /**
     * 详见[ETag的使用](#etag)
     */
    public static class PreconditionFailedException extends BackendException {
        public PreconditionFailedException() {
            super(HttpStatus.PRECONDITION_FAILED, "PreconditionFailed",
                         "The specified If-Match header doesn't match the ETag header.");
        }
    }

    /**
     * Authorization头域中附带的签名和服务端验证不一致
     */
    public static class SignatureDoesNotMatchException extends BackendException {
        public SignatureDoesNotMatchException() {
            super(HttpStatus.FORBIDDEN, "SignatureDoesNotMatch",
                         "The request signature we calculated does not match the signature you provided. Check your "
                                 + "Secret Access Key and signing method. Consult the service documentation for "
                                 + "details."
            );
        }
    }
}
