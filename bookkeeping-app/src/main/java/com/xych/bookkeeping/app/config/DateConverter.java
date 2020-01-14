package com.xych.bookkeeping.app.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateConverter implements Converter<String, Date> {
    private static final List<String> formarts = new ArrayList<>(8);
    static {
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd HH:mm");
        formarts.add("yyyy-MM-dd HH:mm:ss");
        formarts.add("yyyyMM");
        formarts.add("yyyyMMdd");
        formarts.add("yyyyMMddHHmm");
        formarts.add("yyyyMMddHHmmss");
        formarts.add("yyyyMMdd HHmm");
        formarts.add("yyyyMMdd HHmmss");
    }

    @Override
    public Date convert(String source) {
        String value = source.trim();
        if("".equals(value)) {
            return null;
        }
        if(source.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(source, formarts.get(0));
        }
        else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source, formarts.get(1));
        }
        else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formarts.get(2));
        }
        else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formarts.get(3));
        }
        else if(source.matches("^\\d{6}$")) {
            return parseDate(source, formarts.get(4));
        }
        else if(source.matches("^\\d{8}")) {
            return parseDate(source, formarts.get(5));
        }
        else if(source.matches("^\\d{12}$")) {
            return parseDate(source, formarts.get(6));
        }
        else if(source.matches("^\\d{14}$")) {
            return parseDate(source, formarts.get(7));
        }
        else if(source.matches("^\\d{8} {1}\\d{4}")) {
            return parseDate(source, formarts.get(8));
        }
        else if(source.matches("^\\d{8} {1}\\d{6}")) {
            return parseDate(source, formarts.get(9));
        }
        else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }

    /**
     * 格式化日期
     * @param dateStr String 字符型日期
     * @param format String 格式
     * @return Date 日期
     */
    private Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        }
        catch(Exception e) {
        }
        return date;
    }
}
