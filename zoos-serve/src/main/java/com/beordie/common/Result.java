package com.beordie.common;

import lombok.Data;

/**
 * @Description 响应体结构
 * @Date 2022/12/22 20:20
 * @Created beordie
 */
@Data
public class Result<T> {
    /**
     * 消息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;
    /**
     * 响应码
     */
    private Integer code;

    public static final Result PARAM = new Result();
    public static final Result ACCESS = new Result();

    static {
        PARAM.code = HttpCode.FAILED_PARAM.getCode();
        PARAM.message = HttpCode.FAILED_PARAM.getDescribe();
        ACCESS.code = HttpCode.FAILED_ACCESS.getCode();
        ACCESS.message = HttpCode.FAILED_ACCESS.getDescribe();
    }

    public Result() {
        success();
    }

    public Result(T data) {
        this.data = data;
        success();
    }

    /**
     * 成功请求
     */
    public void success() {
        this.code = HttpCode.SUCCESS.getCode();
        this.message = HttpCode.SUCCESS.getDescribe();
    }

    /**
     * 失败请求
     * @param <T>
     * @return
     */
    public static <T> Result failed() {
        Result<T> result = new Result<>();
        result.code = HttpCode.FAILED.getCode();
        result.message = HttpCode.FAILED.getDescribe();
        return result;
    }

    /**
     * 参数失败请求
     * @param <T>
     * @return
     */
    public static <T> Result failedParam() {
        return PARAM;
    }

    public static <T> Result failedAccess() {
        return ACCESS;
    }

    /**
     * 设置响应码
     * @param code 响应码 HttpCode enum
     */
    public void setCode(HttpCode code) {
        this.code = code.getCode();
        this.message = code.getDescribe();
    }

    /**
     * 额外设置响应的数据
     * @param data 资源
     */
    public void setData(T data) {
        this.data = data;
    }
}
