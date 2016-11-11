/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.utils.exceptions;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.http.HttpStatus;

/**
 * This is the base exception class for the whole project, any other new
 * exception used must extend from BaseException, it is necessary to produce a
 * correct error response.
 *
 * @author edcracken
 */
public class BaseException extends RuntimeException {

    private final String code;
    private final HttpStatus httpStatus;

    public BaseException(HttpStatus httpStatus, String code, String message) {
        super(message, null);
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public BaseException(HttpStatus httpStatus, String code, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getUrl() {
        return "http://ms.com/errors/#" + code;
    }

    @JsonIgnore
    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

}
