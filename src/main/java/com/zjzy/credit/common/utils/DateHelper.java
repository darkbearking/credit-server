package com.zjzy.credit.common.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zjzy.credit.common.consts.Consts;

/**
 * 日期助手类.
 * @title DateHelper
 * @description 日期助手类. 
 * @author zhiwei.han
 * @date 2019年8月11日
 */
public class DateHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateHelper.class);

    private static final String COMMON_FULLDATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 东八区数值常量
     */
    private static final int EAST8_TIMEZONE = 8;

    private DateHelper() {
    }

    /**
     * 当前时刻的date.
     * @return 当前时刻.
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 将指定毫秒数转化为相应日期并格式化为完整日期,包含时分秒.
     * @param millisecond
     * @return
     */
    public static String normalDateFormat(long millisecond) {
        if (millisecond == 0L) {
            return null;
        }
        try {
            return DateFormatUtils.format(new Date(millisecond), COMMON_FULLDATE_PATTERN, TimeZone.getTimeZone("GMT"));
        } catch (Exception e) {
            LOGGER.error("formart date exception with value:" + millisecond, e);
            return null;
        }
    }

    /**
     * 将指定的日期转换为完整日期格式，包含时分秒
     * @param date 
     * @return
     */
    public static String normalDateFormat(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, COMMON_FULLDATE_PATTERN);
    }

    /**
     * 按照指定的格式进行日期的格式化.
     * @param date 日期,支持null值。
     * @param formatter 格式字串
     * @return 格式化后的字符串
     */
    public static String formatWithNullSafe(Date date, String formatter) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, formatter);
    }

    /**
     * 解析日期字符串为date对象，
     * date对象自动+8小时以适配东八区的情况。
     * 支持的字符串格式只有OneDataConsts.DATE_PATTERN_DAY, OneDataConsts.DATE_PATTERN_MONTH两种。
     * @param dateStr 日期字符串
     * @return 日期对象
     * @throws ParseException 解析异常
     */
    public static Date parseWithTimezoneSupport(String dateStr) throws ParseException {
        Date date = DateUtils.parseDate(dateStr, Consts.DATE_PATTERN_DAY, Consts.DATE_PATTERN_MONTH);
        date = DateUtils.addHours(date, EAST8_TIMEZONE);
        return date;
    }
}
