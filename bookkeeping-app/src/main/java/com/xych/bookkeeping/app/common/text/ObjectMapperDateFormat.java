package com.xych.bookkeeping.app.common.text;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        super();
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
            Date date = DateUtils.parseDate(source, parsePatterns);
            if(!Objects.isNull(date)) {
                // 这里使用lang3的工具类，pos没有发生变化
                // 所以super.parse(String)会报错
                pos.setIndex(1);
            }
            return date;
        }
        catch(ParseException e) {
            dateFormat.parse(source, pos);
        }
        return null;
    }

    // 此方法在objectmapper 默认的dateformat里边用到，这里也要重写
    // 代码：com.fasterxml.jackson.databind.cfg.BaseSettings._force(DateFormat, TimeZone)
    @Override
    public Object clone() {
        DateFormat dateFormat = (DateFormat) this.dateFormat.clone();
        return new ObjectMapperDateFormat(dateFormat);
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapperDateFormat df = new ObjectMapperDateFormat(format);
        Date date = df.parse("2020-03-02T15:37:02.134Z");
        System.out.println(date);
        System.out.println(format.format(date));
    }
}
