package com.xych.bookkeeping.app.drools.utils;

import java.text.MessageFormat;

public class RuleUtil {
    /**
     * 组装规则文件drl的package
     * @author Lanboo
     * @date 2020年1月16日下午10:43:06
     */
    public static String drlPackage(String sceneId, String id) {
        return MessageFormat.format("com.xych.rules.{0}.{1}", sceneId, id);
    }

    public static String drlPath(String sceneId, String id) {
        return MessageFormat.format("src/main/resources/rules/{0}/{1}", sceneId, id);
    }
}
