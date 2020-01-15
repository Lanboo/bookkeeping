package com.xych.bookkeeping.dao.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.xych.bookkeeping.dao.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_record_rule_detail")
public class RecordRuleDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
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
