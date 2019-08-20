package com.zjzy.credit.common.exception;

/**
 * 内部异常.
 * @title InternalException
 * @description 内部异常. 
 * @author hanzhiwei
 * @date 2016年10月19日
 * @version 1.0
 */
public class InternalException extends BaseRuntimeException {
    private static final long serialVersionUID = 5722585775196688979L;

    public InternalException() {
        super("Internal exception.");
    }

    public InternalException(String errMsg) {
        super(errMsg);
    }

    public InternalException(Throwable cause) {
        super(cause);
    }

    public InternalException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }
}
