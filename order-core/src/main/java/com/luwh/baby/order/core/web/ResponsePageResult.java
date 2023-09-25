package com.luwh.baby.order.core.web;

import org.springframework.http.HttpStatus;

/**
 * @author lu.wh
 * @date 2023/09/12 11/01/17
 * @description
 */
public class ResponsePageResult<T> {
    private Integer code;
    private String message;
    private T data;
    private boolean success;

    public ResponsePageResult() {
    }

    public ResponsePageResult(Integer code, String message, T data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public static <T> ResponsePageResult success(Integer code, T data, String message){
        return new ResponsePageResult(code, message, data, true);
    }

    public static <T> ResponsePageResult success(T data, String message){
        return new ResponsePageResult(HttpStatus.OK.value(), message, data, true);
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
