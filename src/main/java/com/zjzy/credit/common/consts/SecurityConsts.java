package com.zjzy.credit.common.consts;

/**
 * @description 安全相关常量 
 * @author zhiwei.han
 * @date 2019-08-13 16:10:22
 */
public final class SecurityConsts {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_PRINCIPAL = "Principal";
    // token相关常量
    public static final String TOKEN_BEARER = "bearer";
    public static final String TOKEN_USER = "user";
    public static final String TOKEN_ACCTTYPE = "aty";
    public static final String TOKEN_ACCTTYPE_USER = "usr";
    public static final String TOKEN_ACCTTYPE_SYS = "sys";
    public static final String TOKEN_ENV = "env";
    public static final String TOKEN_SALT = "slt";

    // token解密秘钥
    public static final String JWT_SECRET = "zjzy-base";
}
