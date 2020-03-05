package com.xych.bookkeeping.dao.entity;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.xych.bookkeeping.dao.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_record")
public class Record extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 用户代码
     */
    private String userCode;
    /**
     * 业务场景
     */
    private String busiType;
    /**
     * 账本
     */
    private Long accountBook;
    /**
     * 金额，分
     */
    private Long amount;
    /**
     * 资金流向:1:收入,-1:支出
     */
    private Integer flow;
    /**
     * 分类
     */
    private Long category;
    /**
     * 账户
     */
    private Long asset;
    /**
     * 时间
     */
    private Date recordTime;
    /**
     * 备注
     */
    private String recordDesc;
    /**
     * 成员(允许多成员平摊)
     */
    private String familyMember;
    /**
     * 支付宝流水表ID
     */
    private Long alipayRecordId;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
    /**
     * 操作人
     */
    private String operator;
    // 非数据库字段
    /**
     * 消费开始时间
     */
    @Transient
    private Date recordTimeStart;
    /**
     * 消费结束时间
     */
    @Transient
    private Date recordTimeEnd;
}
