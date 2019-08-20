package com.zjzy.credit.common.exception;

/**
 * 基础运行时异常类，无需显性捕获处理.
 * @title BaseRuntimeException
 * @description 基础运行时异常类.
 * @author hanzhiwei
 * @date 2016年7月19日
 * @version 1.0
 */
public abstract class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 7336512712126834854L;

    public BaseRuntimeException() {
    }

    public BaseRuntimeException(String errMsg) {
        super(errMsg);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

    public BaseRuntimeException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }
}
