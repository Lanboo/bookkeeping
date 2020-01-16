package com.xych.bookkeeping.app.drools.utils;

import java.text.MessageFormat;

import com.xych.bookkeeping.app.common.enums.BusiTypeEnum;

public class RuleUtil {
    /**
     * 组装规则文件drl的package
     * @author Lanboo
     * @date 2020年1月16日下午10:43:06
     */
    public static String drlPackage(BusiTypeEnum busiType, String id, String sceneId) {
        return MessageFormat.format("com.xych.rules.{0}.scene_{1}.{2}", busiType.getCode(), sceneId, id);
    }

    public static String drlPath(BusiTypeEnum busiType, String id, String sceneId) {
        return MessageFormat.format("rules/{0}/scene_{1}/{2}", busiType.getCode(), sceneId, id);
    }
}
