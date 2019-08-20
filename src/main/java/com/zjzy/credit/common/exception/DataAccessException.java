package com.zjzy.credit.common.exception;

/**
 * 数据访问异常.
 * @title DataAccessException
 * @description 数据访问异常. 
 * @author hanzhiwei
 * @date 2016年10月19日
 * @version 1.0
 */
public class DataAccessException extends BaseRuntimeException {
    private static final long serialVersionUID = 1523433420873604073L;

    public DataAccessException() {
        super("Data acccess exception.");
    }

    public DataAccessException(String errMsg) {
        super(errMsg);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }
}
