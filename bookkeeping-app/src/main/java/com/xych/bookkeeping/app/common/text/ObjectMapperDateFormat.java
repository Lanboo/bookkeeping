package com.xych.bookkeeping.app.common.text;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 自定义反序列化方法
 * @Description
 * @author 晓月残魂
 * @CreateDate 2020年3月2日上午12:30:09
 */
public class ObjectMapperDateFormat extends SimpleDateFormat {
    private static final String[] parsePatterns;
    static {
        List<String> formarts = new ArrayList<>(12);
        formarts.add("yyyy-MM-dd HH:mm:ss");
        formarts.add("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formarts.add("yyyyMMddHHmmss");
        formarts.add("yyyy-MM-dd HH:mm:ss.SSS");
        formarts.add("yyyyMMddHHmmssSSS");
        formarts.add("yyyyMMdd HHmmss");
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd HH:mm");
        formarts.add("yyyyMM");
        formarts.add("yyyyMMdd");
        formarts.add("yyyyMMddHHmm");
        formarts.add("yyyyMMdd HHmm");
        parsePatterns = formarts.toArray(new String[0]);
    }
    private static final long serialVersionUID = 1L;
    private DateFormat dateFormat;

    public ObjectMapperDateFormat(DateFormat dateFormat) {
        //构造函数传入objectmapper默认的dateformat
        this.dateFormat = dateFormat;
    }

    /**
     * 序列化方法
     */
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return dateFormat.format(date, toAppendTo, fieldPosition);
    }

    /**
     * 反序列化方法
     */
    @Override
    public Date parse(String source, ParsePosition pos) {
        try {
            return DateUtils.parseDate(source, parsePatterns);
        }
        catch(ParseException e) {
            dateFormat.parse(source, pos);
        }
        return null;
    }
}
