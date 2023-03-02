package com.beordie.common;

/**
 * @Description 状态码
 * @Date 2022/12/22 20:31
 * @Created beordie
 */
public enum HttpCode {
    SUCCESS(200, "Success"),
    FAILED(500, "Failed"),
    FAILED_PARAM(400, "parameters error, please check."),
    FAILED_ACCESS(401, "access error");

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述
     */
    private String describe;

    private HttpCode(int code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public int getCode() {
        return this.code;
    }

    public String getDescribe() {
        return this.describe;
    }
}
