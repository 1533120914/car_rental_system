package com.iweb.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期和时间工具类
 */
public class DateUtil {
    // 定义一个静态的转换器
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 字符串转换为LocalDateTime
    public static LocalDateTime stringToLocalDateTime(String dateStr) {
        return dateStr == null ? null : LocalDateTime.parse(dateStr, DTF);
    }

    // LocalDateTime转为字符串
    public static String localDateTimeToString(LocalDateTime date){
        return date == null ? null : date.format(DTF);
    }
}
