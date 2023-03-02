package com.beordie.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * @Description
 * @Version 1
 * @Date 2023/3/2 9:06
 * @User beordie
 */
@Aspect
@Component
public class AccessAspect {
    @Pointcut("@annotation(com.beordie.common.AccessAnnotation)")
    private void LogPoint() {}

    @Before("LogPoint()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        Enumeration<String> role = request.getHeaders("role");
        if (role == null) throw new RuntimeException("");
    }
}
