package com.zjzy.credit.common.exception;

/**
 * 权限相关异常.
 * @title AuthException
 * @description 权限相关异常.
 * @author hanzhiwei
 * @date 2016年12月28日
 * @version 1.0
 */
public class AuthException extends BaseRuntimeException {

    private static final long serialVersionUID = 6336671988937607053L;

    public AuthException() {
        super("Illegal Parameter");
    }

    public AuthException(String errMsg) {
        super(errMsg);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }
}
