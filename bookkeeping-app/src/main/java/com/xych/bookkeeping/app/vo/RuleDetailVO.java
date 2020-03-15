package com.xych.bookkeeping.app.vo;

import com.xych.bookkeeping.app.vo.base.BasePageVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RuleDetailVO extends BasePageVO {
    private static final long serialVersionUID = 1L;
    /**
     * 规则Id
     */
    private Long ruleId;
    /**
     * 序号
     */
    private Integer idx;
    /**
     * 源字段
     */
    private String originField;
    /**
     * 关系:等于,大于,小于,大于等于,小于等于,不等于,包含,不包含
     */
    private String originOperator;
    /**
     * 源字段值
     */
    private String originFieldValue;
}
