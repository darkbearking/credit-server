package com.zjzy.credit.common.exception;

/**
 * 业务异常.
 * @title BusinessException
 * @description 业务异常. 
 * @author hanzhiwei
 * @date 2016年11月8日
 * @version 1.0
 */
public class BusinessException extends BaseRuntimeException {

    private static final long serialVersionUID = 7805981291350841911L;

    public BusinessException() {
        super("Business exception.");
    }

    public BusinessException(String errMsg) {
        super(errMsg);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
