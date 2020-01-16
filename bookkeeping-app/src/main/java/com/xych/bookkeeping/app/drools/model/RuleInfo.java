package com.xych.bookkeeping.app.drools.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class RuleInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 规则id，全局唯一
     */
    private String id;
    /**
     * 场景id，一个场景对应多个规则，一个场景对应一个业务场景，一个场景对应一个kmodule
     */
    private String sceneId;
    /**
     * 规则内容，既drl文件内容
     */
    private String content;
}
