package com.luwh.we.app.core.web;

import com.luwh.we.app.core.web.enums.ResponseEnums;
import org.springframework.http.HttpStatus;

/**
 * @author lu.wh
 * @date 2023/09/12 11/01/08
 * @description
 */
public class ResponseResult<T> {
    private Integer code;
    private String message;
    private T data;
    private boolean success;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String message, T data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public static <T> ResponseResult success(Integer code, T data, String message){
        return new ResponseResult(code, message, data, true);
    }

    public static <T> ResponseResult success(T data, String message){
        return new ResponseResult(HttpStatus.OK.value(), message, data, true);
    }

    public static <T> ResponseResult success(T data){
        return new ResponseResult(HttpStatus.OK.value(), null, data, true);
    }

    public static <T> ResponseResult error(ResponseEnums enums, String message) {
        return new ResponseResult(enums.code(), message, null, false);
    }

    public static <T> ResponseResult error(ResponseEnums enums, String message, String data) {
        return new ResponseResult(enums.code(), message, data, false);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
