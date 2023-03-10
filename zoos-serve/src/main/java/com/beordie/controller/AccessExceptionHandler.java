package com.beordie.controller;

import com.beordie.common.Result;
import com.beordie.common.exception.AccessRuntimeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/8 9:13
 * @User beordie
 */
@ControllerAdvice
public class AccessExceptionHandler {
    @ExceptionHandler(value = AccessRuntimeException.class)
    public Result exHandler1(Exception ex){
        return Result.ACCESS;
    }
}
