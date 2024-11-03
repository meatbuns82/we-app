package com.luwh.we.app.common.exception;

import com.luwh.we.app.common.exception.exceptions.OrderException;
import com.luwh.we.app.common.response.OrderResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author lu.wh
 * @date 2024/08/25 22/26/24
 * @description
 */
@ControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler
    public OrderResponseEntity exception(Exception e){
        if(e instanceof OrderException){

        }
        return OrderResponseEntity.error(e.getMessage());
    }
}
