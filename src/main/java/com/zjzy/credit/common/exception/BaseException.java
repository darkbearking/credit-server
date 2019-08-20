package com.zjzy.credit.common.exception;

/**
 * 基础异常类，需要显性捕获处理.
 * @title BaseException
 * @description 基础异常类，需要显性捕获处理. 
 * @author hanzhiwei
 * @date 2016年10月19日
 * @version 1.0
 */
public abstract class BaseException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1078770311263405949L;

    public BaseException() {
    }

    public BaseException(String errMsg) {
        super(errMsg);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }

}
