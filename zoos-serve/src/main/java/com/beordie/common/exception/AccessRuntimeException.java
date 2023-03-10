package com.beordie.common.exception;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/8 9:04
 * @User beordie
 */
public class AccessRuntimeException extends Exception {
    public AccessRuntimeException() {
        super("没有权限");
    }
}
