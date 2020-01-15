package com.xych.bookkeeping.dao.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.xych.bookkeeping.dao.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_record_rule")
public class RecordRule extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    /**
     * 用户代码
     */
    private String userCode;
    /**
     * 目标字段
     */
    private String targetField;
    /**
     * 输出结果
     */
    private String targetFieldValue;
    /**
     * 明细表达式
     */
    private String ruleEngine;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
}
