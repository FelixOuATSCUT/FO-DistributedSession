package com.example.demo.exception;

/**
 * @author felix.ou
 */

public enum SessionExceptionCode {

    ER_INTERNAL(500, "系统错误"),
    ER_UNKNOWN(501, "未知错误"),

    ER_REDIS(400, "Redis异常");

    /**
     * 返回信息描述
     */
    private String msg;

    /**
     * 返回码
     */
    private int code;

    SessionExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String msg() {return this.msg;}
}
