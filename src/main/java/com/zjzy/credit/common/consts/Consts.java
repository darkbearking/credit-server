package com.zjzy.credit.common.consts;

/**
 * @title Consts
 * @description 系统常量. 
 * @author zhiwei.han
 * @date 2019年8月11日
 */
public final class Consts {
    public static final String SLASH = "/";
    public static final String UTF8 = "utf-8";
    public static final String EMPTY = "";
    public static final String SEMICOLON = ";";
    public static final String SPACE = " ";
    public static final String QUESTION_MARK = "?";
    public static final String AND = "&";
    public static final String EQUAL = "=";
    public static final String UNDER_LINE = "_";
    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String DOT = ".";
    public static final String SHORT_BAR = "-";

    /**
     * 数字常量
     */
    public static final int INT_NUM_0 = 0;
    public static final int INT_NUM_1 = 1;
    public static final int INT_NUM_2 = 2;
    public static final int INT_NUM_3 = 3;
    public static final int INT_NUM_4 = 4;
    public static final int INT_NUM_5 = 5;
    public static final int INT_NUM_6 = 6;
    public static final int INT_NUM_7 = 7;
    public static final int INT_NUM_8 = 8;
    public static final int INT_NUM_9 = 9;
    public static final int INT_NUM_10 = 10;
    public static final int INT_NUM_11 = 11;
    public static final int INT_NUM_12 = 12;
    public static final int INT_NUM_13 = 13;
    public static final int INT_NUM_14 = 14;
    public static final int INT_NUM_15 = 15;
    public static final int INT_NUM_16 = 16;

    /**
     *  应答结果状态码——成功
     */
    public static final int RESULT_CODE_SUCCESS = 0;
    /**
     *  应答结果状态码——通用错误
     */
    public static final int RESULT_CODE_COMMONERR = 9999;
    /**
     *  NOT Exists
     */
    public static final int RESULT_CODE_NOTEXIST = 1000;
    /**
     *  business error
     */
    public static final int RESULT_CODE_BUSINESSERR = 1100;
    /**
     *  远程调用错误
     */
    public static final int RESULT_CODE_RPCERR = 1200;
    /**
     *  认证失败-无效token
     */
    public static final int RESULT_CODE_INVALIDTOKEN = 1300;

    /**
     *  日期格式
     */
    public static final String DATE_PATTERN = "yyyyMMdd";
    public static final String COMMON_FULLDATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String TIMESTAMP_PATTERN = "yyyyMMddhhmmsss";

    public static final String TIMESTAMP_PATTERN_MONTH = "yyyyMM";
    public static final String TIMESTAMP_PATTERN_DAY = "yyyyMMdd";
    public static final String TIMESTAMP_PATTERN_HOUR = "yyyyMMddhh";
    public static final String TIMESTAMP_PATTERN_MIN = "yyyyMMddhhmm";
    public static final String TIMESTAMP_PATTERN_SEC = "yyyyMMddhhmmsss";

    public static final String DATE_PATTERN_MONTH = "yyyy-MM";
    public static final String DATE_PATTERN_DAY = "yyyy-MM-dd";
    public static final String DATE_PATTERN_HOUR = "yyyy-MM-dd HH";
    public static final String DATE_PATTERN_MIN = "yyyy-MM-dd HH:mm";
    public static final String DATE_PATTERN_SEC = "yyyy-MM-dd HH:mm:ss";
}
