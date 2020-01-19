package com.xych.bookkeeping.app.drools.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RuleInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 业务场景
     */
    private String busiType;
    /**
     * 规则id，用户id
     */
    private String id;
    /**
     * 场景id
     */
    private String sceneId;
    /**
     * 规则内容，既drl文件内容
     */
    private String content;
}
