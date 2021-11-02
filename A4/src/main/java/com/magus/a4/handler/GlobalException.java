package com.magus.a4.handler;

public class GlobalException extends RuntimeException {

    private String errCode;

    private String errMsg;

    public GlobalException() {
        super();
    }

    public GlobalException(String message) {
        this.errMsg = message;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}