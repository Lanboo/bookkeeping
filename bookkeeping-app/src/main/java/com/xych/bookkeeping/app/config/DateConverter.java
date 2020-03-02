package com.xych.bookkeeping.app.config;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateConverter implements Converter<String, Date> {
    private static final String[] parsePatterns;
    static {
        List<String> formarts = new ArrayList<>(12);
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd HH:mm");
        formarts.add("yyyy-MM-dd HH:mm:ss");
        formarts.add("yyyy-MM-dd HH:mm:ss.SSS");
        formarts.add("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formarts.add("yyyyMM");
        formarts.add("yyyyMMdd");
        formarts.add("yyyyMMddHHmm");
        formarts.add("yyyyMMddHHmmss");
        formarts.add("yyyyMMddHHmmssSSS");
        formarts.add("yyyyMMdd HHmm");
        formarts.add("yyyyMMdd HHmmss");
        parsePatterns = formarts.toArray(new String[0]);
    }

    @Override
    public Date convert(String source) {
        String value = source.trim();
        if("".equals(value)) {
            return null;
        }
        try {
            return DateUtils.parseDate(source, parsePatterns);
        }
        catch(ParseException e) {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'", e);
        }
    }

    public static void main(String[] args) {
        DateConverter c = new DateConverter();
        // Z,UTC日期,即世界时间,与北京时间相差八个时区。
        // 按照yyyy-MM-dd'T'HH:mm:ss.SSS'Z'格式解析后就是本地local时间
        System.out.println(c.convert("2020-03-02T02:43:19.280Z"));
    }
}
