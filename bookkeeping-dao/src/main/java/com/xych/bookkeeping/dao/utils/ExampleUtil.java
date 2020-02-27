package com.xych.bookkeeping.dao.utils;

import org.apache.commons.lang3.StringUtils;

import tk.mybatis.mapper.entity.Example;

public class ExampleUtil {
    public static class Criteria {
        public static void addLike(Example.Criteria criteria, String fieldName, String fieldValue) {
            if(StringUtils.isNotEmpty(fieldValue)) {
                criteria.andLike(fieldName, "%" + fieldValue + "%");
            }
        }

        public static void addLeftLike(Example.Criteria criteria, String fieldName, String fieldValue) {
            if(StringUtils.isNotEmpty(fieldValue)) {
                criteria.andLike(fieldName, "%" + fieldValue);
            }
        }

        public static void addRightLike(Example.Criteria criteria, String fieldName, String fieldValue) {
            if(StringUtils.isNotEmpty(fieldValue)) {
                criteria.andLike(fieldName, fieldValue + "%");
            }
        }
    }
}
