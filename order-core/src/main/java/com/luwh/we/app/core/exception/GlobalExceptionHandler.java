package com.luwh.we.app.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lu.wh
 * @date 2023/09/25 18/00/38
 * @description
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public void handle(Exception e){
        logger.info("exception...{}", e);
        throw new RuntimeException(e);
    }
}
