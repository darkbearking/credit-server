package com.zjzy.credit.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @title ThreadUtils
 * @description 线程操作工具类  
 * @author zhiwei.han
 * @date 2019年8月11日
 */
public class ThreadUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadUtils.class);

    private ThreadUtils() {
    }

    /**
     * 让当前线程安静的sleep指定的毫秒数，
     * 不会有异常抛出 。
     * @param millis 毫秒数
     */
    public static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LOGGER.error("thread sleep exception.", e);
        }
    }
}
