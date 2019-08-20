package com.zjzy.credit.common.exception;

/**
 * 未找到相应数据时抛出的异常.
 * @title NotFoundException
 * @description 未找到相应数据时抛出的异常.
 * @author hanzhiwei
 * @date 2017年1月12日
 * @version 1.0
 */
public class NotFoundException extends BaseException {
    private static final long serialVersionUID = 5412390401122424712L;

    public NotFoundException() {
        super("Not Found.");
    }

    public NotFoundException(String errMsg) {
        super(errMsg);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }
}
