package com.luwh.we.app.common.response;

import org.springframework.http.HttpStatus;

/**
 * @author lu.wh
 * @date 2024/08/25 22/32/00
 * @description
 */
public class OrderResponseEntity<T> {
    private int code;
    private String message;
    private String errorDetail;
    private T data;
    private String api;

    public OrderResponseEntity() {
    }

    public OrderResponseEntity(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public OrderResponseEntity(int code, String message, String errorDetail) {
        this.code = code;
        this.message = message;
        this.errorDetail = errorDetail;
    }


    public OrderResponseEntity(int code, String message, T data, String api) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.api = api;
    }

    public static <T> OrderResponseEntity<T> ok(T data){
        return new OrderResponseEntity<>(HttpStatus.OK.value(), "request success", data);
    }

    public static OrderResponseEntity error(String errorDetail){

        return new OrderResponseEntity<>(HttpStatus.OK.value(), "request fail", errorDetail);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
