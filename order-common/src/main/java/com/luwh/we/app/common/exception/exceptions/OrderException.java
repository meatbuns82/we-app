package com.luwh.we.app.common.exception.exceptions;

/**
 * @author lu.wh
 * @date 2024/08/25 22/38/11
 * @description
 */
public class OrderException extends RuntimeException{

    public OrderException() {
    }

    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
