package com.zjzy.credit.server.base;

/**
 * @description 当前用户登录信息holder 
 * @author zhiwei.han
 * @date 2019-08-13 16:04:45
 */
public class CurrentIdentityInfoHolder {
    /**
     * 当前登录用户名holder
     */
    private static ThreadLocal<String> userKeyHolder = new ThreadLocal<>();

    /**
     * 设置登录用户的key
     * @param userKey 用户key
     */
    public static void setUserKey(String userKey) {
        userKeyHolder.set(userKey);
    }

    /**
     * 获取用户名
     *
     * @return userkey
     */
    public static String getUserKey() {
        if (userKeyHolder.get() != null) {
            return userKeyHolder.get();
        }
        return null;
    }

}
