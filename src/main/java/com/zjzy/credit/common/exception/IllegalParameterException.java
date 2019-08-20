package com.zjzy.credit.common.exception;

/**
 * 非法传参异常.
 * @title IllegalParameterException
 * @description 非法传参异常. 
 * @author hanzhiwei
 * @date 2016年10月19日
 * @version 1.0
 */
public class IllegalParameterException extends BaseRuntimeException {
    private static final long serialVersionUID = -6277013304097552489L;

    public IllegalParameterException() {
        super("Illegal Parameter");
    }

    public IllegalParameterException(String errMsg) {
        super(errMsg);
    }

    public IllegalParameterException(Throwable cause) {
        super(cause);
    }
}
