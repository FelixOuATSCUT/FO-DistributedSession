package com.example.demo.exception;

/**
 * @author felix.ou
 */
public class SessionException extends Exception {

    /**
     * 错误信息
     */
    private String message;
    /**
     * 错误码
     */
    private SessionExceptionCode msgCode;

    public SessionException(SessionExceptionCode msgCode) {
        this.msgCode = msgCode;
        this.message = msgCode.msg();
    }

    public SessionException(SessionExceptionCode msgCode, String msg) {
        this.msgCode = msgCode;
        this.message = String.format("%s[%s]", msgCode.msg(), msg);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public SessionExceptionCode getMsgCode() {
        return msgCode;
    }
}
